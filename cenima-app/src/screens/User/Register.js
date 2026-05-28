import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Register.css'; 

const Register = () => {
    // 1. Tạo state lưu trữ dữ liệu người dùng nhập
    const [formData, setFormData] = useState({
        name: '',
        numberPhone: '',
        username: '',
        password: '',
        confirmPassword: ''
    });

    // State riêng để lưu file ảnh avatar
    const [avatarFile, setAvatarFile] = useState(null);

    // 2. Hàm bắt sự kiện khi gõ phím vào các ô text
    const handleInputChange = (e) => {
        const { id, value } = e.target;
        setFormData({
            ...formData,
            [id]: value
        });
    };

    // 3. Hàm bắt sự kiện khi chọn file ảnh
    const handleFileChange = (e) => {
        if (e.target.files && e.target.files[0]) {
            setAvatarFile(e.target.files[0]);
        }
    };

    // 4. Hàm xử lý khi bấm nút "Đăng Ký Ngay"
    const handleRegister = (e) => {
        e.preventDefault(); // Ngăn trình duyệt load lại trang

        // Kiểm tra mật khẩu khớp nhau
        if (formData.password !== formData.confirmPassword) {
            alert("Mật khẩu xác nhận không khớp!");
            return;
        }

        // ĐÓNG GÓI DỮ LIỆU ĐỂ GỬI CHO SPRING BOOT (Do có MultipartFile)
        const data = new FormData();
        
        // Key ở đây (name, username, password...) PHẢI KHỚP với tên biến trong class Users.java
        data.append("name", formData.name);
        data.append("numberPhone", formData.numberPhone);
        data.append("username", formData.username);
        data.append("password", formData.password);

        // Nạp file ảnh vào (Khớp với @Transient private MultipartFile file;)
        if (avatarFile) {
            data.append("file", avatarFile);
        } else {
            alert("Vui lòng chọn ảnh đại diện!");
            return;
        }

        // --- GHI CHÚ CHO BẠN ---
        // Chỗ này bạn dùng fetch hoặc axios để gọi API Spring Boot
        console.log("Dữ liệu chuẩn bị gửi đi:", formData);
        console.log("File ảnh đính kèm:", avatarFile.name);
        
        /* Cấu trúc gọi API mẫu:
        fetch('http://localhost:8080/api/users/register', {
            method: 'POST',
            body: data // Chú ý: KHÔNG set Content-Type là application/json khi dùng FormData
        })
        .then(res => res.json())
        .then(result => console.log(result))
        .catch(err => console.error(err));
        */
       
       alert("Đã gom dữ liệu thành công! Mở F12 Console để xem thử nhé.");
    };

    return (
        <div className="register-container">
            <div className="register-box">
                <h2 className="register-title">Đăng Ký Tài Khoản</h2>
                <p className="register-subtitle">Trở thành thành viên của CineBook ngay hôm nay</p>

                {/* Gắn hàm handleRegister vào sự kiện onSubmit */}
                <form className="register-form" onSubmit={handleRegister}>
                    
                    <div className="form-row">
                        <div className="input-group">
                            <label htmlFor="name">Họ và tên</label>
                            <input type="text" id="name" value={formData.name} onChange={handleInputChange} placeholder="VD: Nguyễn Văn A" className="register-input" required />
                        </div>
                        <div className="input-group">
                            <label htmlFor="numberPhone">Số điện thoại</label>
                            <input type="tel" id="numberPhone" value={formData.numberPhone} onChange={handleInputChange} placeholder="090xxxxxxx" className="register-input" required />
                        </div>
                    </div>

                    <div className="input-group">
                        <label htmlFor="username">Tên đăng nhập (Username)</label>
                        <input type="text" id="username" value={formData.username} onChange={handleInputChange} placeholder="Nhập tên đăng nhập viết liền không dấu..." className="register-input" required />
                    </div>

                    <div className="form-row">
                        <div className="input-group">
                            <label htmlFor="password">Mật khẩu</label>
                            <input type="password" id="password" value={formData.password} onChange={handleInputChange} placeholder="Tạo mật khẩu..." className="register-input" required />
                        </div>
                        <div className="input-group">
                            <label htmlFor="confirmPassword">Xác nhận mật khẩu</label>
                            <input type="password" id="confirmPassword" value={formData.confirmPassword} onChange={handleInputChange} placeholder="Nhập lại mật khẩu..." className="register-input" required />
                        </div>
                    </div>

                    <div className="input-group">
                        <label>Ảnh đại diện (Avatar)</label>
                        <div className="file-upload-wrapper">
                            {/* Gắn hàm handleFileChange vào ô input file */}
                            <input type="file" id="file" accept="image/*" onChange={handleFileChange} className="file-input-hidden" />
                            <label htmlFor="file" className="file-upload-btn">
                                <span>📁 Chọn ảnh từ máy tính</span>
                            </label>
                            {/* Hiển thị tên file nếu đã chọn, nếu chưa thì hiện chữ mặc định */}
                            <span className="file-name-display">
                                {avatarFile ? avatarFile.name : "Chưa có tệp nào được chọn"}
                            </span>
                        </div>
                    </div>

                    <button type="submit" className="btn-submit-register">
                        Đăng Ký Ngay
                    </button>
                </form>

                <div className="register-footer">
                    <span>Bạn đã có tài khoản? </span>
                    <Link to="/login" className="login-link">Đăng nhập</Link>
                </div>
            </div>
        </div>
    );
};

export default Register;