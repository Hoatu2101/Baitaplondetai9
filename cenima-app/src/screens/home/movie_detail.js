import './movie.css';
import { useEffect, useState } from 'react';
import { useRef } from 'react'; // THÊM: Import useRef để dùng cho hiệu ứng cuộn trang
import { Container, Row, Col, Card } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import Apis, { endpoints } from '../../configs/Apis';
import MySpinner from '../../components/MySpinner/MySpinner';


import { lazy, Suspense } from 'react';


import SeatMap from './SeatMap'; 

const MovieDetails = () => {
    const ShowtimeList = lazy(() => import('./showtimelist'));
    const { movieId } = useParams();
    const [movie, setMovie] = useState(null);
    const [loading, setLoading] = useState(true);


    const [selectedShowtime, setSelectedShowtime] = useState(null);
    const seatMapRef = useRef(null);

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
    const handleSelectShowtime = (showtimeId) => {
        setSelectedShowtime(showtimeId);
        setTimeout(() => {
            if (seatMapRef.current) {
                seatMapRef.current.scrollIntoView({ behavior: 'smooth', block: 'start' });
            }
        }, 100);
    };

    if (loading) return <div className="spinner-overlay"><MySpinner /></div>;
    if (!movie) return <h3 className="text-white text-center mt-5">Không tìm thấy thông tin phim!</h3>;

    return (
        <div className="movie-details-wrapper">
            <div
                className="hero-banner"
                style={{ backgroundImage: `url(${movie.poster})` }}
            >
                <div className="hero-overlay">
                    <Container className="hero-content">
                        <div className="category-tags">
                            <span className="custom-tag">
                                {movie.category?.name || "Đang cập nhật"}
                            </span>
                        </div>
                        <h1 className="hero-title">{movie.movieName}</h1>

                        <div className="meta-info">
                            <span className="meta-item text-warning">
                                <b>⭐ {movie.rating}/10</b>
                            </span>
                            <span className="meta-item"> Thời lượng: {movie.duration} phút</span>
                            <span className="meta-item"> Định dạng: {movie.movieFormat}</span>
                        </div>
                    </Container>
                </div>
            </div>

            <Container className="content-section mt-4">
                <Row>
                    <Col md={7} lg={8} className="mb-4">
                        <h4 className="section-heading">Nội dung phim</h4>
                        <p className="movie-description">{movie.description}</p>
                    </Col>

                    
                </Row>


                <Row className="mt-5">
                    <Col>
                        <Suspense fallback={<MySpinner />}>
                            {/* THÊM: Truyền prop onSelectShowtime vào đây */}
                            <ShowtimeList movieId={movieId} onSelectShowtime={handleSelectShowtime} />
                        </Suspense>
                    </Col>
                </Row>

  
                <hr style={{ borderColor: '#4b4b8f', margin: '40px 0' }} />
                
                <div ref={seatMapRef}>
                    {selectedShowtime ? (
                        <Row className="mb-5 pb-5">
                            <Col>
                                <SeatMap showtimeId={selectedShowtime} />
                            </Col>
                        </Row>
                    ) : (
                        <div className="text-center py-5 mb-5" style={{ color: '#6a6a9d' }}>
                            <i className="fs-1">💺</i>
                            <p className="mt-3">Sơ đồ ghế sẽ xuất hiện ở đây sau khi bạn chọn suất chiếu.</p>
                        </div>
                    )}
                </div>

            </Container>
        </div>
    );
};

export default MovieDetails;