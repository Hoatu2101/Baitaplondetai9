import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import "./Header.css";
import { MyUserContext } from "../configs/context";
import Apis, { endpoints, authApis } from "../configs/Apis";
import cookies from "react-cookies";

const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const nav = useNavigate(); 
    
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const login = async (e) => {
        e.preventDefault();
        try {
            let res = await Apis.post(endpoints['login'], {
                "username": username, 
                "password": password
            });

            localStorage.setItem("access_token", res.data.token);

            let userRes = await authApis().get(endpoints['current-user']); 
            
            dispatch({
                type: "LOGIN",
                payload: userRes.data 
            });

            nav("/"); 
        } catch (err) {
            console.error("Lỗi đăng nhập", err);
        }
    }

    return (
        <header className="cinema-header">
            <Link to="/" className="logo-container">
                <h1 className="logo-text">CineBook</h1>
            </Link>

            <div className="nav-menu">
                <Link to="/" className="nav-item nav-item-red">
                    Phim
                </Link>
                <Link to="/lich-su-dat-ve" className="nav-item nav-item-white">
                    Vé của tôi
                </Link>

                {user === null ? (
                    <div className="auth-buttons">
                        <Link to="/login" className="btn-login">Đăng nhập</Link>
                        <Link to="/register" className="btn-register">Đăng ký</Link>
                    </div>
                ) : (
                    <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
                        <img
                            src={user.avatar || "https://cdn-icons-png.flaticon.com/512/149/149071.png"} 
                            width={40}
                            height={40}
                            style={{ 
                                objectFit: "cover", 
                                border: "2px solid #fff",
                                backgroundColor: "#fff"
                            }}
                            className="rounded-circle"
                            alt="avatar"
                        />

                        <span style={{ fontWeight: "500", color: "#fff" }}>
                            Xin chào, {user.name || "Khách"}!
                        </span>

                  
                        <Button
                            variant="danger"
                            size="sm"
                            style={{
                                backgroundColor: "#e50914", // Mã màu đỏ của Netflix
                                borderColor: "#e50914",
                                fontWeight: "600",
                                padding: "6px 16px",
                                borderRadius: "20px", // Bo tròn hai đầu
                                boxShadow: "0 2px 4px rgba(0,0,0,0.2)",
                                transition: "all 0.3s ease"
                            }}
                            onClick={() => {
                                dispatch({ type: "LOGOUT" });
                                localStorage.removeItem("access_token"); 
                                nav("/login"); 
                            }}
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