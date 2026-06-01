
import React, { useState, useEffect } from 'react';
import {
    Row,
    Col,
    Card,
    Button,
    Alert
} from 'react-bootstrap';

import { useNavigate, useParams } from 'react-router-dom';

import './Home.css';

import MySpinner from '../../components/Myspinner/MySpinner';

import Apis, { endpoints } from '../../configs/Apis';
import { useSearchParams } from 'react-router-dom';
const Home = () => {
    const [isEnd, setIsEnd] = useState(false);
    const [movies, setMovies] = useState([]);
    const [kw, setKw] = useState();
    const [loading, setLoading] = useState(true);
    const [categories, setCategories] = useState([]);
    const [page, setPage] = useState(1);
    const [q, setQ] = useSearchParams();
    const nav = useNavigate();
    const search = (e) => {
        e.preventDefault();

        if (kw && kw.trim() !== "") {
            q.set("kw", kw);
        } else {
            q.delete("kw");
        }
        setQ(q);
    };
    const loadMore = () => {
        setPage(page + 1);
    }
    const loadCates = async () => {
        let res = await Apis.get(endpoints['categories']);
        setCategories(res.data);
    }
    const loadMovies = async () => {
        setLoading(true);

        try {
            // Xây dựng URL với query parameters
            let url = endpoints['movies'];
            let queryParams = [];
            queryParams.push(`page=${page}`);
            const cateId = q.get("cateId");
            if (cateId) {
                queryParams.push(`cateId=${cateId}`);
            }
            const kw = q.get("kw");
            if (kw) {
                queryParams.push(`kw=${kw}`);
            }
            if (queryParams.length > 0) {
                url += `?${queryParams.join("&")}`;
            }

            let res = await Apis.get(url);
            if (page === 1) {
                setMovies(res.data);
            } else {
                setMovies([...movies, ...res.data]);
            }
            if (res.data.length < 20) {
                setIsEnd(true);
            } else {
                setIsEnd(false);
            }
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadMovies();
    }, [q, page]);
    useEffect(() => {
        loadCates();
    }, []);
    useEffect(() => { setPage(1) }, [q]);
    return (
        <div className="home-container">

            {/* HEADER */}
            <div className="home-header">
                <h1 className="main-title">
                    Đặt Vé Xem Phim Online
                </h1>

                <p className="sub-title">
                    Tìm và đặt vé cho các bộ phim yêu thích của bạn
                </p>
            </div>

            {/* SEARCH */}
            <div className="search-section">
                <form className="search-box" onSubmit={search}>

                    <svg
                        className="search-icon"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth="2"
                    >
                        <circle cx="11" cy="11" r="8"></circle>

                        <line
                            x1="21"
                            y1="21"
                            x2="16.65"
                            y2="16.65"
                        ></line>
                    </svg>

                    <input
                        type="text"
                        placeholder="Tìm kiếm phim..."
                        className="search-input-main"
                        value={kw || ""}
                        onChange={(e) => setKw(e.target.value)}
                    />
                </form>
            </div>

            {/* FILTER */}
            <div className="filter-section">

                <div className="filter-title">

                    <svg
                        className="filter-icon"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth="2"
                    >
                        <polygon points="22 3 2 3 10 12.46 10 19 14 21 14 12.46 22 3"></polygon>
                    </svg>

                    <span>Bộ lọc</span>
                </div>

                <div className="filter-controls">

                    <div className="filter-group">
                        <label>Thể loại</label>

                        <select
                            className="filter-select"
                            value={q.get("cateId") || ""}
                            onChange={(e) => {
                                const value = e.target.value;

                                if (value === "") {
                                    nav("/");
                                } else {
                                    nav(`/?cateId=${value}`);
                                }
                            }}
                        >
                            <option value="">Tất cả</option>

                            {categories.map((c) => (
                                <option key={c.id} value={c.id}>
                                    {c.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="filter-group price-group">
                        <label>Giá: 0đ - 150.000đ</label>

                        <input
                            type="range"
                            min="0"
                            max="150000"
                            className="price-slider"
                        />
                    </div>

                    <div className="filter-group">
                        <label>Sắp xếp theo</label>

                        <select className="filter-select">
                            <option>Đánh giá cao nhất</option>
                            <option>Mới ra mắt</option>
                            <option>Giá thấp đến cao</option>
                        </select>
                    </div>
                </div>
            </div>



            {movies.length === 0 && (
                <Col xs={12}>
                    <div className="empty-state-container">

                        {/* Icon cuộn phim */}
                        <svg viewBox="0 0 24 24" width="80" height="80" stroke="currentColor" strokeWidth="1" fill="none" className="empty-icon">
                            <rect x="2" y="2" width="20" height="20" rx="2.18" ry="2.18"></rect>
                            <line x1="7" y1="2" x2="7" y2="22"></line>
                            <line x1="17" y1="2" x2="17" y2="22"></line>
                            <line x1="2" y1="12" x2="22" y2="12"></line>
                            <line x1="2" y1="7" x2="7" y2="7"></line>
                            <line x1="2" y1="17" x2="7" y2="17"></line>
                            <line x1="17" y1="17" x2="22" y2="17"></line>
                            <line x1="17" y1="7" x2="22" y2="7"></line>
                        </svg>

                        <h3 className="empty-title">Không tìm thấy phim nào</h3>
                        <p className="empty-subtitle">
                            Rất tiếc, hiện tại không có bộ phim nào khớp với bộ lọc của bạn.<br />
                            Vui lòng thử lại với một thể loại hoặc từ khóa khác!
                        </p>

                    </div>
                </Col>
            )}
            <Row className="g-4">
                {movies.map((p) => (
                    <Col xs={12} sm={6} md={4} lg={3} key={p.id}>

                        <Card className="movie-card-custom h-100" onClick={() => nav(`/movies/${p.id}`)}>


                            <div className="poster-container">
                                <Card.Img
                                    variant="top"
                                    src={p.poster || "https://res.cloudinary.com/dxxwcby8l/image/upload/v1717013892/Cinemax-Placeholder-Gold-Star_d3k4e0.jpg"}
                                    className="poster-img"
                                />
                                <div className="rating-badge">
                                    <svg viewBox="0 0 24 24" width="14" height="14" fill="#ffc107">
                                        <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon>
                                    </svg>
                                    <span>{p.rating || "8.9"}</span>
                                </div>
                            </div>


                            <Card.Body className="d-flex flex-column info-container">


                                <Card.Title className="movie-title">
                                    {p.movieName}
                                </Card.Title>


                              
                                <div className="tags-container">
                                    <span className="tag">
                                        {p.category ? p.category: "Đang cập nhật"}
                                    </span>
                                </div>


                                <div className="bottom-meta mt-auto">
                                    <span className="duration">
                                        Thời lượng: {p.duration} phút
                                    </span>
                                </div>

                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
            {loading && (
                <div className="spinner-overlay">
                    <MySpinner />
                </div>
            )}


            {movies.length > 0 && (
                <div className="d-flex justify-content-center mt-5 mb-5">


                    {!isEnd ? (
                        <button
                            className="btn-load-more-netflix"
                            onClick={loadMore}
                        >
                            {loading ? 'Đang tải...' : 'Xem thêm phim'}
                        </button>
                    ) : (


                        <div className="end-of-list-msg">
                            <span className="fs-5">🍿</span>
                            <p>Bạn đã khám phá hết danh sách phim hiện tại!</p>
                        </div>

                    )}

                </div>
            )}
        </div>
    );
};

export default Home;