import React, { useState, useEffect } from "react";
import { Container, Button, Spinner } from "react-bootstrap";
// QUAN TRỌNG: Đảm bảo import đúng đường dẫn đến file Apis.js của bạn
import Apis, { authApis, endpoints } from "../../configs/Apis";
import "./SeatMap.css";
import cookies from "react-cookies";

const SeatMap = ({ showtimeId }) => {
    const [seats, setSeats] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [loading, setLoading] = useState(true);

    const validId = showtimeId && typeof showtimeId === 'object' ? showtimeId.id : showtimeId;

    useEffect(() => {
        const fetchSeats = async () => {
            if (!validId || validId === "[object Object]") {
                console.warn("SeatMap: showtimeId chưa hợp lệ hoặc chưa được chọn.");
                setLoading(false);
                return;
            }

            setLoading(true);
            try {
                // Gọi API lấy cấu trúc ghế (Không dính 302 nếu Backend đã cấu hình permitAll)
                // Sử dụng endpoints.seats nếu đã cấu hình trong Apis.js
                const seatEndpoint = endpoints.seats ? endpoints.seats(validId) : `/showtimes/${validId}/seats`;
                let res = await Apis.get(seatEndpoint);
                setSeats(res.data || []);
            } catch (err) {
                console.error("Lỗi tải sơ đồ ghế:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchSeats();
        setSelectedSeats([]); // Reset ghế đang chọn khi đổi suất chiếu
    }, [showtimeId, validId]);

    const handleSeatClick = (seat) => {
        if (seat.status === "BOOKED" || seat.status === "SOLD") return;

        const currentId = seat.id || seat.seatId;

        setSelectedSeats((prev) => {
            const isAlreadySelected = prev.find((s) => (s.id || s.seatId) === currentId);

            if (isAlreadySelected) {
                return prev.filter((s) => (s.id || s.seatId) !== currentId);
            } else {
                // Đang giới hạn chọn 1 ghế/lần theo logic của bạn
                if (prev.length >= 1) {
                    alert("Bạn chỉ được chọn tối đa 1 ghế cho mỗi lần đặt vé!");
                    return prev;
                }
                return [...prev, seat];
            }
        });
    };

    const groupSeatsByRow = () => {
        const rows = {};
        if (!Array.isArray(seats)) return rows;

        seats.forEach((seat) => {
            const seatName = seat.name || seat.seatNumber;
            const rowLetter = seatName ? seatName.charAt(0).toUpperCase() : "?";
            if (!rows[rowLetter]) {
                rows[rowLetter] = [];
            }
            rows[rowLetter].push(seat);
        });

        Object.keys(rows).forEach((rowKey) => {
            rows[rowKey].sort((a, b) => {
                const nameA = a.name || a.seatNumber;
                const nameB = b.name || b.seatNumber;
                const numA = parseInt(nameA?.substring(1)) || 0;
                const numB = parseInt(nameB?.substring(1)) || 0;
                return numA - numB;
            });
        });

        return rows;
    };

    const seatRows = groupSeatsByRow();
    const totalPrice = selectedSeats.reduce((sum, seat) => sum + (seat.price || 75000), 0);

const handleCheckout = async () => {
        if (selectedSeats.length === 0) {
            alert("Vui lòng chọn ghế!");
            return;
        }

        const token = cookies.load("token");
        if (!token) {
            alert("Vui lòng đăng nhập để tiếp tục đặt vé!");
            return;
        }

        const bookingData = {
            showtimeId: validId,
            seatIds: selectedSeats.map(s => s.id || s.seatId) 
        };

        try {
            console.log("Đang thực hiện giao dịch với data: ", bookingData);

            // 1. GỌI BẰNG authApis() ĐỂ GẮN TOKEN (Sửa Apis thành authApis())
            try {
                await authApis().post("/secure/lock-seats", bookingData);
            } catch (lockError) {
                 console.error("Lỗi khi khóa ghế:", lockError);
                 alert("Không thể khóa ghế. Vui lòng thử lại!");
                 return; 
            }

            // 2. GỌI BẰNG authApis() ĐỂ ĐẶT VÉ
            const bookingEndpoint = endpoints.booking || "/secure/bookings";
            const res = await authApis().post(bookingEndpoint, bookingData);

            // 3. CODE PHÒNG THỦ: Phát hiện Spring Security chuyển hướng HTML
            if (typeof res.data === 'string' && res.data.toLowerCase().includes('<!doctype html>')) {
                alert("Phiên đăng nhập đã hết hạn hoặc không hợp lệ. Vui lòng đăng nhập lại!");
                // Gợi ý: Chuyển hướng người dùng về trang đăng nhập ở đây
                return;
            }

            if (res.status === 200 || res.status === 201) {
                alert(`Đặt vé thành công! Mã đơn hàng của bạn là: #${res.data}`);
                
                setSelectedSeats([]);

                // Tải lại sơ đồ ghế
                const seatEndpoint = endpoints.seats ? endpoints.seats(validId) : `/showtimes/${validId}/seats`;
                const reload = await Apis.get(seatEndpoint);
                setSeats(reload.data || []);
            }

        } catch (err) {
            console.error("Chi tiết lỗi đặt vé:", err);
            if (err.response && (err.response.status === 401 || err.response.status === 403 || err.response.status === 302)) {
                 alert("Phiên đăng nhập không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại!");
            } else {
                 alert("Lỗi đặt vé. Hệ thống đang gặp sự cố, xin thử lại sau.");
            }
        }
    };

    return (
        <Container className="seatmap-container my-5 p-4 rounded shadow-lg">
            <h3 className="text-center text-white mb-4 fw-bold">CHỌN GHẾ NGỒI</h3>

            <div className="seat-legend d-flex justify-content-center flex-wrap gap-4 mb-5">
                <div className="legend-item d-flex align-items-center gap-2">
                    <div className="seat-icon available"></div> <span className="text-white">Ghế trống</span>
                </div>
                <div className="legend-item d-flex align-items-center gap-2">
                    <div className="seat-icon selected"></div> <span className="text-white">Đang chọn</span>
                </div>
                <div className="legend-item d-flex align-items-center gap-2">
                    <div className="seat-icon booked"></div> <span className="text-white">Đã bán</span>
                </div>
            </div>

            <div className="screen-container text-center mb-5">
                <div className="cinema-screen"></div>
                <p className="text-muted mt-3 fw-bold" style={{ letterSpacing: "5px", fontSize: "0.9rem" }}>MÀN HÌNH</p>
            </div>

            <div className="seats-grid d-flex flex-column align-items-center mb-4">
                {Object.keys(seatRows).length > 0 ? (
                    Object.keys(seatRows).sort().map((rowKey) => (
                        <div key={rowKey} className="seat-row d-flex align-items-center mb-2">
                            <div className="row-label text-warning fw-bold me-3 text-center" style={{ width: "25px" }}>{rowKey}</div>

                            <div className="d-flex gap-2 flex-wrap">
                                {seatRows[rowKey].map((seat) => {
                                    const currentId = seat.id || seat.seatId;
                                    const isSelected = selectedSeats.some((s) => (s.id || s.seatId) === currentId);
                                    const isBooked = seat.status === "BOOKED" || seat.status === "SOLD";
                                    const seatName = seat.name || seat.seatNumber;

                                    return (
                                        <div
                                            key={currentId}
                                            className={`seat-box d-flex align-items-center justify-content-center rounded ${isBooked ? "booked" : isSelected ? "selected" : "available"
                                                }`}
                                            onClick={() => handleSeatClick(seat)}
                                            title={`Ghế ${seatName}`}
                                        >
                                            <span className="seat-number">
                                                {seatName ? seatName.substring(1) : ""}
                                            </span>
                                        </div>
                                    );
                                })}
                            </div>

                            <div className="row-label text-warning fw-bold ms-3 text-center" style={{ width: "25px" }}>{rowKey}</div>
                        </div>
                    ))
                ) : (
                    <div className="text-center py-4 border border-secondary border-dashed rounded w-100" style={{ maxWidth: "500px", color: "#8a8a8a" }}>
                        <span className="fs-3 d-block mb-2">⚠️</span>
                        <p className="mb-0 fw-medium">Không thể hiển thị sơ đồ ghế.</p>
                        <small className="text-muted d-block mt-1">Vui lòng đảm bảo bạn đã cấu hình permitAll cho API này tại Spring Security Backend.</small>
                    </div>
                )}
            </div>

            {selectedSeats.length > 0 && (
                <div className="checkout-bar mt-5 d-flex justify-content-between align-items-center p-4 rounded shadow bg-dark border border-secondary">
                    <div>
                        <p className="mb-1 text-light fs-5">
                            Ghế chọn: <span className="text-warning fw-bold">{selectedSeats.map(s => s.name || s.seatNumber).join(", ")}</span>
                        </p>
                        <h3 className="text-danger fw-bold mb-0">
                            Tổng: {totalPrice.toLocaleString("vi-VN")} đ
                        </h3>
                    </div>
                    <Button variant="warning" size="lg" className="fw-bold px-4 py-2" onClick={handleCheckout}>
                        TIẾP TỤC {`>>`}
                    </Button>
                </div>
            )}
        </Container>
    );
};

export default SeatMap;