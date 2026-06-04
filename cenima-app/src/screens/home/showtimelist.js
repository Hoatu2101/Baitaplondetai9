import { useEffect, useState } from "react";
import Apis, { endpoints } from "../../configs/Apis";
import "./movie.css";
const ShowtimeList = ({ movieId, onSelectShowtime }) => {
    const [showtimes, setShowtimes] = useState([]);
    const [selectedId, setSelectedId] = useState(null);
    
    useEffect(() => {
        const loadShowtimes = async () => {
            try {
                const res = await Apis.get(endpoints.movie_showtimes(movieId));
             
                
                setShowtimes(res.data);
            } catch (err) {
                console.error("Lỗi khi tải lịch chiếu:", err);
            }
        };

        if (movieId) loadShowtimes();
    }, [movieId]);


    const formatTime = (time) => {
        if (!time) return "--:--";

        const date = new Date(time);
        if (isNaN(date.getTime())) return "--:--";

        return date.toLocaleTimeString("vi-VN", {
            hour: "2-digit",
            minute: "2-digit",
        });
    };

    const groupByCinema = (data) => {
        if (!Array.isArray(data)) return {};
        
        return data.reduce((groups, st) => {
            
            const cinemaName = st.cinemaName || st.cinema?.name || st.room?.cinema?.name || "Rạp chưa xác định";
            
            if (!groups[cinemaName]) {
                groups[cinemaName] = [];
            }
            groups[cinemaName].push(st);
            return groups;
        }, {});
    };

    const handleSelect = (st) => {
        setSelectedId(st.id);
        if (onSelectShowtime) {
            onSelectShowtime(st); 
        }
    };

    const groupedShowtimes = groupByCinema(showtimes);
    return (
        <div className="showtime-container my-4">
        <h4 className="section-heading mb-4 fw-bold text-uppercase">Lịch chiếu phim</h4>

        {Object.keys(groupedShowtimes).length > 0 ? (
            Object.keys(groupedShowtimes).map((cinemaName) => (

                <div key={cinemaName} className="cinema-group mb-4 p-3 border rounded">
                    
               
                    <div className="cinema-name mb-3 d-flex align-items-center">
                        <span className="fs-5 fw-semibold">🎬 {cinemaName}</span>
                    </div>
                    
                   
                    <div className="d-flex flex-wrap gap-2">
                        {groupedShowtimes[cinemaName].map((st) => (
                            <button
                                key={st.id}
                                onClick={() => handleSelect(st)}
                                className={`btn px-3 py-2 fw-medium ${
                                    selectedId === st.id
                                        ? "btn-warning"
                                        : "btn-outline-warning"
                                }`}
                            >
                                {formatTime(st.startTime)}
                            </button>
                        ))}
                    </div>
                </div>
            ))
        ) : (
            <div className="alert-light border text-muted py-3 px-3 rounded" role="alert">
                Hiện tại chưa có suất chiếu nào cho bộ phim này.
            </div>
        )}
    </div>
    );
};

export default ShowtimeList;