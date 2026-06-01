import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css'; 

const Header = () => {
    return (
        <header className="cinema-header">
            {/* Cụm Logo bên trái */}
            <Link to="/" className="logo-container">
                <svg className="logo-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                    <rect x="2" y="2" width="20" height="20" rx="2.18" ry="2.18"></rect>
                    <line x1="7" y1="2" x2="7" y2="22"></line>
                    <line x1="17" y1="2" x2="17" y2="22"></line>
                    <line x1="2" y1="12" x2="22" y2="12"></line>
                    <line x1="2" y1="7" x2="7" y2="7"></line>
                    <line x1="2" y1="17" x2="7" y2="17"></line>
                    <line x1="17" y1="17" x2="22" y2="17"></line>
                    <line x1="17" y1="7" x2="22" y2="7"></line>
                </svg>
                <h1 className="logo-text">CineBook</h1>
            </Link>

            {/* Cụm Menu bên phải */}
            <div className="nav-menu">
                {/* Nút Phim */}
                <Link to="/Home" className="nav-item nav-item-red">
                    <svg className="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <rect x="2" y="2" width="20" height="20" rx="2.18" ry="2.18"></rect>
                        <line x1="7" y1="2" x2="7" y2="22"></line>
                        <line x1="17" y1="2" x2="17" y2="22"></line>
                        <line x1="2" y1="12" x2="22" y2="12"></line>
                    </svg>
                    <span>Phim</span>
                </Link>

                {/* Nút Vé của tôi */}
                <Link to="/lich-su-dat-ve" className="nav-item nav-item-white">
                    <svg className="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <path d="M4 7V5c0-1.1.9-2 2-2h12c1.1 0 2 .9 2 2v2c-1.1 0-2 .9-2 2s.9 2 2 2v2c0 1.1-.9 2-2 2H6c-1.1 0-2-.9-2-2v-2c1.1 0 2-.9 2-2s-.9-2-2-2z"></path>
                        <line x1="9" y1="12" x2="15" y2="12"></line>
                    </svg>
                    <span>Vé của tôi</span>
                </Link>

                {/* --- CỤM NÚT ĐĂNG NHẬP / ĐĂNG KÝ --- */}
                <div className="auth-buttons">
                    <Link to="/Login" className="btn-login">Đăng nhập</Link>
                    <Link to="/Register" className="btn-register">Đăng ký</Link>
                </div>
            </div>
        </header>
    );
}

export default Header;