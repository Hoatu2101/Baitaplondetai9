import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";

import "./Header.css";
import { MyUserContext } from "../configs/context";

const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);

    return (
        <header className="cinema-header">

            {/* Logo */}
            <Link to="/" className="logo-container">
                <h1 className="logo-text">CineBook</h1>
            </Link>

            {/* Menu */}
            <div className="nav-menu">

                <Link to="/Home" className="nav-item nav-item-red">
                    Phim
                </Link>

                <Link to="/lich-su-dat-ve" className="nav-item nav-item-white">
                    Vé của tôi
                </Link>

                {/* AUTH AREA */}
                {user === null ? (
                    <div className="auth-buttons">
                        <Link to="/login" className="btn-login">Đăng nhập</Link>
                        <Link to="/register" className="btn-register">Đăng ký</Link>
                    </div>
                ) : (
                    <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>

                        {user.avatar && (
                            <img
                                src={user.avatar}
                                width={40}
                                className="rounded-circle"
                                alt="avatar"
                            />
                        )}

                        <span>Chào {user.username}!</span>

                        <Button
                            variant="info"
                            onClick={() => dispatch({ type: "LOGOUT" })}
                        >
                            Đăng xuất
                        </Button>
                    </div>
                )}

            </div>
        </header>
    );
};

export default Header;