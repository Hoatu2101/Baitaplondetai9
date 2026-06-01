import './movie.css';
import { useEffect, useState } from 'react';
import { Container, Row, Col, Card, Form, Button, Image } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import Apis, { endpoints } from '../../configs/Apis';
import MySpinner from '../../components/Myspinner/MySpinner';

const MovieDetails = () => {
    // 1. GỌI HOOKS Ở ĐÂY (Cấp cao nhất của Component)
    const { movieId } = useParams();
    const [movie, setMovie] = useState(null);
    const [loading, setLoading] = useState(true);
    const [selectedTheater, setSelectedTheater] = useState("");

    // 2. HÀM GỌI API (Chỉ là hàm bình thường thôi)
    const loadProduct = async () => {
        setLoading(true);
        try {
            if (movieId) {
                let res = await Apis.get(
                    endpoints['detail_movie'](movieId)
                );

                setMovie(res.data);
            }
        } catch (ex) {
            console.error("Lỗi:", ex);
        } finally {
            setLoading(false);
        }
    }


    useEffect(() => {
        loadProduct();
    }, [movieId]);

    // 4. Xử lý UI
    if (loading) return <div className="spinner-overlay"><MySpinner /></div>;
    if (!movie) return <h3 className="text-white text-center mt-5">Không tìm thấy thông tin phim!</h3>;

    return (
        <div className="movie-details-wrapper">
            {/* HERO BANNER - KHU VỰC ẢNH MỜ Ở TRÊN */}
            <div
                className="hero-banner"
                style={{ backgroundImage: `url(${movie.poster})` }}
            >
                <div className="hero-overlay">
                    <Container className="hero-content">
                        {/* Thẻ tag thể loại */}
                        <div className="category-tags">
                            <span className="custom-tag">
                                {movie.category.name || "Đang cập nhật"}
                            </span>
                        </div>
                        {/* Tên phim */}
                        <h1 className="hero-title">{movie.movieName}</h1>

                        {/* Các thông tin nhỏ (Rating, Thời lượng, Ngày chiếu...) */}
                        <div className="meta-info">
                            <span className="meta-item text-warning">
                                <b>⭐ {movie.rating}/10</b>
                            </span>
                            <span className="meta-item">⏱ {movie.duration} phút</span>
                            <span className="meta-item">📅 {movie.releaseDate}</span>
                            <span className="meta-item">🌐 {movie.language}</span>
                        </div>
                    </Container>
                </div>
            </div>

            {/* PHẦN NỘI DUNG VÀ ĐẶT VÉ BÊN DƯỚI */}
            <Container className="content-section">
                <Row>
                    {/* CỘT TRÁI: NỘI DUNG PHIM */}
                    <Col md={7} lg={8} className="mb-4">
                        <h4 className="section-heading">Nội dung phim</h4>
                        <p className="movie-description">{movie.description}</p>
                    </Col>

                    {/* CỘT PHẢI: KHUNG ĐẶT VÉ */}
                    <Col md={5} lg={4}>
                        <Card className="booking-card">
                            <Card.Body>
                                <h3 className="booking-price">
                                    {new Intl.NumberFormat('vi-VN').format(movie.price)}đ
                                </h3>
                                <p className="text-muted small mb-4">Giá vé mỗi ghế</p>

                                <Form.Group className="mb-4">
                                    <Form.Label className="theater-label">📍 Chọn rạp chiếu</Form.Label>
                                    <Form.Select
                                        className="dark-select"
                                        value={selectedTheater}
                                        onChange={(e) => setSelectedTheater(e.target.value)}
                                    >
                                        <option value="">-- Chọn rạp --</option>
                                        <option value="1">Rạp Phê La Quận 1</option>
                                        <option value="2">Rạp Sinh Viên Đại Học Mở</option>
                                    </Form.Select>
                                </Form.Group>

                                {/* Nút chỉ sáng lên khi đã chọn rạp */}
                                <Button
                                    className="btn-book-seat w-100"
                                    disabled={!selectedTheater}
                                >
                                    Chọn ghế {'>'}
                                </Button>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};
export default MovieDetails;