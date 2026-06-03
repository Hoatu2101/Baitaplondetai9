import React, { useState, useContext } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import './Login.css';

import Apis, { endpoints, authApis } from '../../configs/Apis';
import cookies from 'react-cookies';
import { MyUserContext } from '../../configs/context';

const Login = () => {
    const [user, setUser] = useState({
        username: "",
        password: ""
    });

    const [err, setErr] = useState("");
    const [loading, setLoading] = useState(false);

    const [, dispatch] = useContext(MyUserContext);
    const [q] = useSearchParams();
    const nav = useNavigate();

    const handleChange = (e) => {
        setUser({
            ...user,
            [e.target.id]: e.target.value
        });
    };

    const validate = () => {
        if (!user.username || !user.password) {
            setErr("Vui lòng nhập đầy đủ thông tin!");
            return false;
        }
        setErr("");
        return true;
    };

    const login = async (e) => {
        e.preventDefault();

        if (!validate()) return;

        try {
            setLoading(true);

           
            let res = await Apis.post(endpoints['login'], user);
            cookies.save('token', res.data.token);

            
            let p = await authApis().get(endpoints['profile']);
            cookies.save('user', p.data);

        
            dispatch({
                type: "LOGIN",
                payload: p.data
            });
            let next = q.get('next');
            nav(next ? next : '/');

        } catch (ex) {
            console.error(ex);
            setErr("Đăng nhập thất bại!");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="login-container">
            <div className="login-box">
                <h2 className="login-title">Đăng Nhập</h2>
                <p className="login-subtitle">Chào mừng bạn trở lại với CineBook</p>

                {err && <p style={{ color: 'red' }}>{err}</p>}

                <form className="login-form" onSubmit={login}>
                    
                    {/* Username */}
                    <div className="input-group">
                        <label htmlFor="username">Tên đăng nhập</label>
                        <input
                            type="text"
                            id="username"
                            value={user.username}
                            onChange={handleChange}
                            placeholder="Nhập tên đăng nhập..."
                            className="login-input"
                        />
                    </div>

                    {/* Password */}
                    <div className="input-group">
                        <label htmlFor="password">Mật khẩu</label>
                        <input
                            type="password"
                            id="password"
                            value={user.password}
                            onChange={handleChange}
                            placeholder="Nhập mật khẩu..."
                            className="login-input"
                        />
                    </div>

                    {/* Forgot password */}
                    <div className="forgot-password">
                        <a href="#!">Quên mật khẩu?</a>
                    </div>

                    {/* Submit */}
                    <button
                        type="submit"
                        className="btn-submit-login"
                        disabled={loading}
                    >
                        {loading ? "Đang đăng nhập..." : "Đăng Nhập"}
                    </button>
                </form>

                <div className="login-footer">
                    <span>Bạn chưa có tài khoản? </span>
                    <Link to="/register" className="register-link">
                        Đăng ký ngay
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Login;