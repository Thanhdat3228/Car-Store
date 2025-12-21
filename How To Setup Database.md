# Hướng dẫn Setup Database cho Website Bán Xe

## Yêu cầu
- MySQL Server đã được cài đặt
- MySQL Workbench hoặc phpMyAdmin (hoặc command line)

## Các bước thực hiện

### Bước 1: Mở MySQL
--Mở XAMPP control panel
--Start MySQL lên rồi chọn Admin

### Bước 2: Chạy Script SQL
1. Mở file `database_setup.sql` 
2. Copy toàn bộ nội dung
3. Paste vào MySQL và chạy

### Bước 3: Kiểm tra
Sau khi chạy script, bạn sẽ có:
- Database: `car_store`
- Bảng: `cars` với 6 xe mẫu

### Bước 4: Cấu hình kết nối (nếu cần)
--password và port(3306) em để mặc định ko cần cấu hình lại

## Cấu trúc bảng cars

| Cột | Kiểu dữ liệu | Mô tả |
|-----|-------------|-------|
| id | INT | ID tự động tăng (Primary Key) |
| brand | VARCHAR(50) | Thương hiệu xe |
| model | VARCHAR(100) | Mẫu xe |
| year | INT | Năm sản xuất |
| km | INT | Số km đã đi |
| price | BIGINT | Giá bán (VNĐ) |
| location | VARCHAR(100) | Địa điểm |
| description | TEXT | Mô tả chi tiết |
| image | VARCHAR(255) | Đường dẫn ảnh |
| created_at | TIMESTAMP | Thời gian tạo |

