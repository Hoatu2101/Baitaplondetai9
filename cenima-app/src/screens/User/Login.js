import React from 'react';
import { Link } from 'react-router-dom';
import './Login.css'; 

const Login = () => {
    return (
        <div className="login-container">
            <div className="login-box">
                <h2 className="login-title">Đăng Nhập</h2>
                <p className="login-subtitle">Chào mừng bạn trở lại với CineBook</p>

                <form className="login-form">
                    {/* Ô nhập Email */}
                    <div className="input-group">
                        <label htmlFor="email">Email hoặc Số điện thoại</label>
                        <input 
                            type="text" 
                            id="email" 
                            placeholder="Nhập email của bạn..." 
                            className="login-input"
                        />
                    </div>

                    {/* Ô nhập Mật khẩu */}
                    <div className="input-group">
                        <label htmlFor="password">Mật khẩu</label>
                        <input 
                            type="password" 
                            id="password" 
                            placeholder="Nhập mật khẩu..." 
                            className="login-input"
                        />
                    </div>

                    {/* Quên mật khẩu */}
                    <div className="forgot-password">
                        <a href="#!">Quên mật khẩu?</a>
                    </div>

                    {/* Nút Đăng nhập */}
                    <button type="submit" className="btn-submit-login">
                        Đăng Nhập
                    </button>
                </form>

                {/* Chuyển sang trang Đăng ký */}
                <div className="login-footer">
                    <span>Bạn chưa có tài khoản? </span>
                    <Link to="/register" className="register-link">Đăng ký ngay</Link>
                </div>
            </div>
        </div>
    );
};

export default Login;