-- ============================================
-- Script tạo Database cho Website Bán Xe
-- ============================================

-- Tạo database (nếu chưa tồn tại)
CREATE DATABASE IF NOT EXISTS car_store;
USE car_store;

-- Tạo bảng cars
CREATE TABLE IF NOT EXISTS cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    km INT NOT NULL,
    price BIGINT NOT NULL,
    location VARCHAR(100) NOT NULL,
    description TEXT,
    image VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Chèn dữ liệu mẫu (tùy chọn)
INSERT INTO cars (brand, model, year, km, price, location, description, image) VALUES
('Toyota', 'Camry 2.5Q', 2020, 45000, 850000000, 'Hà Nội', 'Xe nhập khẩu nguyên chiếc, bảo dưỡng định kỳ đầy đủ. Nội thất sang trọng, tiết kiệm nhiên liệu.', 'image/toyota-cambry.jpg'),
('Honda', 'Civic RS', 2019, 60000, 720000000, 'TP. Hồ Chí Minh', 'Xe thể thao, động cơ mạnh mẽ, phù hợp cho giới trẻ yêu thích tốc độ.', 'image/honda-civic.jpg'),
('Mazda', 'Mazda 3', 2021, 30000, 450000000, 'Đà Nẵng', 'Thiết kế hiện đại, công nghệ tiên tiến, an toàn cao.', 'image/mazda-3.jpg'),
('Ford', 'Ranger Wildtrak', 2018, 80000, 650000000, 'Hà Nội', 'Xe bán tải mạnh mẽ, phù hợp địa hình khó khăn.', 'image/fordranger.jpg'),
('KIA', 'Carnival Signature 2.5', 2022, 12000, 1590000000, 'TP. Hồ Chí Minh', 'Xe 7 chỗ sang trọng, đầy đủ tiện nghi, phù hợp gia đình.', 'image/KIA.jpg'),
('BMW', 'BMW 2025', 2025, 90000, 1330000000, 'Hà Nội', 'Xe cao cấp, công nghệ hiện đại nhất.', 'image/BMW.jpg');

-- Kiểm tra dữ liệu
SELECT * FROM cars;

