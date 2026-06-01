import React from 'react';
import { Link } from 'react-router-dom';
import './Footer.css'; 

const Footer = () => {
    return (
        <footer className="cinema-footer">
            <div className="footer-container">
                {/* Cột 1: Thông tin thương hiệu */}
                <div className="footer-col brand-col">
                    <h2 className="footer-brand"><span className="text-red">CINE</span>BOOK</h2>
                    <p className="footer-desc">
                        Hệ thống đặt vé xem phim trực tuyến hàng đầu. Mang đến trải nghiệm điện ảnh đỉnh cao, đặt vé nhanh chóng và tiện lợi nhất.
                    </p>
                </div>

                {/* Cột 2: Đường dẫn nhanh */}
                <div className="footer-col">
                    <h3 className="footer-title">Khám Phá</h3>
                    <div className="footer-links">
                        <Link to="/phim" className="footer-link">Phim Đang Chiếu</Link>
                        <Link to="/phim" className="footer-link">Phim Sắp Chiếu</Link>
                        <Link to="/lich-su-dat-ve" className="footer-link">Vé Của Tôi</Link>
                    </div>
                </div>

                {/* Cột 3: Chính sách & Hỗ trợ */}
                <div className="footer-col">
                    <h3 className="footer-title">Hỗ Trợ</h3>
                    <div className="footer-links">
                        <a href="#" className="footer-link">Điều khoản sử dụng</a>
                        <a href="#" className="footer-link">Chính sách bảo mật</a>
                        <a href="#" className="footer-link">Liên hệ & Góp ý</a>
                    </div>
                </div>
            </div>

            {/* Dòng bản quyền cuối cùng */}
            <div className="footer-bottom">
                <p>Copyright © 2024 CineBook. All rights reserved.</p>
            </div>
        </footer>
    );
}

export default Footer;