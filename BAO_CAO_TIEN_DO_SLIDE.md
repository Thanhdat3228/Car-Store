# BÁO CÁO TIẾN ĐỘ DỰ ÁN
## Website Bán Xe Ô Tô - Car Store

---

## SLIDE 1: GIỚI THIỆU DỰ ÁN

### **Tên dự án:** Car Store - Website Bán Xe Ô Tô

### **Mô tả:**
- Nền tảng trực tuyến cho phép người dùng xem danh sách xe ô tô đang được rao bán
- Hỗ trợ người bán đăng tin bán xe với đầy đủ thông tin và hình ảnh
- Giao diện hiện đại, thân thiện với người dùng

### **Mục tiêu:**
- Xây dựng website bán xe ô tô đơn giản, hiệu quả
- Áp dụng kiến thức Java Web (JSP, Servlet)
- Kết nối với cơ sở dữ liệu MySQL

---

## SLIDE 2: CÔNG NGHỆ SỬ DỤNG

### **Backend:**
- **Java SE 17** - Ngôn ngữ lập trình chính
- **Jakarta Servlet API 4.0.3** - Xử lý request/response
- **JSP (JavaServer Pages)** - Tạo giao diện động
- **Tomcat 9.0** - Web Application Server

### **Frontend:**
- **HTML5, CSS3** - Cấu trúc và styling
- **Responsive Design** - Tương thích nhiều thiết bị

### **Database:**
- **MySQL 8.0+** - Hệ quản trị cơ sở dữ liệu
- **MySQL Connector/J 9.5.0** - Kết nối Java-MySQL

---

## SLIDE 3: KIẾN TRÚC HỆ THỐNG

### **Mô hình MVC (Model-View-Controller):**

#### **Model:**
- `Car.java` - Lớp đối tượng đại diện cho xe ô tô
- Các thuộc tính: id, brand, model, year, mileage, price, location, description, image

#### **View:**
- `index.html` - Trang chủ
- `home.jsp` - Danh sách xe đang bán
- `car_detail.jsp` - Chi tiết xe
- `sellCar.html` - Form đăng bán xe
- `gioi-thieu.html`, `news.html` - Trang tĩnh

#### **Controller:**
- `CarDetailServlet.java` - Xử lý xem chi tiết xe
- `SellCarServlet.java` - Xử lý đăng bán xe mới

#### **DAO (Data Access Object):**
- `CarDAO.java` - Truy vấn dữ liệu từ database

---

## SLIDE 4: CƠ SỞ DỮ LIỆU

### **Database:** `car_store`

### **Bảng `cars`:**
```sql
- id: INT (Primary Key, Auto Increment)
- brand: VARCHAR(50) - Thương hiệu xe
- model: VARCHAR(100) - Mẫu xe
- year: INT - Năm sản xuất
- km: INT - Số km đã đi
- price: BIGINT - Giá bán (VNĐ)
- location: VARCHAR(100) - Địa điểm
- description: TEXT - Mô tả chi tiết
- image: VARCHAR(255) - Đường dẫn hình ảnh
- created_at: TIMESTAMP - Thời gian tạo
```

### **Dữ liệu mẫu:**
- Đã có 6 xe mẫu trong database
- Bao gồm các hãng: Toyota, Honda, Mazda, Ford, KIA, BMW

---

## SLIDE 5: CHỨC NĂNG ĐÃ HOÀN THÀNH

### ✅ **1. Trang Chủ (index.html)**
- Giao diện hero section với banner
- Menu điều hướng đầy đủ
- Nút "Xem xe ngay" và "Đăng bán xe"

### ✅ **2. Danh Sách Xe (home.jsp)**
- Hiển thị tất cả xe đang được rao bán
- Hiển thị dạng grid với thẻ card
- Thông tin: hình ảnh, thương hiệu, model, năm, km, giá, địa điểm
- Click vào xe để xem chi tiết
- Xử lý lỗi khi không kết nối được database

### ✅ **3. Chi Tiết Xe (car_detail.jsp)**
- Hiển thị đầy đủ thông tin xe
- Layout 2 cột: hình ảnh bên trái, thông tin bên phải
- Mô tả chi tiết
- Nút liên hệ và yêu thích
- Nút quay lại danh sách

### ✅ **4. Đăng Bán Xe (sellCar.html)**
- Form đăng bán với các trường:
  - Mã xe, thương hiệu, mẫu xe
  - Năm sản xuất, số km, giá bán
  - Địa điểm, mô tả
  - Upload hình ảnh
- Validation các trường bắt buộc

---

## SLIDE 6: CHỨC NĂNG ĐÃ HOÀN THÀNH (tiếp)

### ✅ **5. Xử Lý Servlet**

#### **CarDetailServlet:**
- Nhận parameter `id` từ URL
- Lấy thông tin xe từ database qua DAO
- Forward đến trang chi tiết
- Xử lý lỗi: ID không hợp lệ, không tìm thấy xe

#### **SellCarServlet:**
- Xử lý form đăng bán (POST)
- Upload file hình ảnh (MultipartConfig)
- Lưu thông tin xe vào database
- Xử lý dữ liệu: brand, model, year, km, price, location, description

### ✅ **6. DAO Layer**
- `CarDAO.java` với phương thức `getCarById(int id)`
- Kết nối database sử dụng JDBC
- Sử dụng PreparedStatement để tránh SQL Injection
- Xử lý exception và đóng kết nối đúng cách

### ✅ **7. Giao Diện**
- CSS responsive, hiện đại
- Sử dụng Google Fonts (Inter)
- Color scheme nhất quán
- Hỗ trợ mobile-friendly

---

## SLIDE 7: CẤU TRÚC THƯ MỤC

```
Car-Store/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/
│   │   │   │   ├── CarDetailServlet.java
│   │   │   │   └── SellCarServlet.java
│   │   │   ├── dao/
│   │   │   │   └── CarDAO.java
│   │   │   └── model/
│   │   │       └── Car.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── lib/ (JAR dependencies)
│   │       │   └── web.xml
│   │       ├── css/
│   │       ├── image/
│   │       ├── index.html
│   │       ├── home.jsp
│   │       ├── car_detail.jsp
│   │       ├── sellCar.html
│   │       ├── gioi-thieu.html
│   │       └── news.html
├── database_setup.sql
└── How To Setup Database.md
```

---

## SLIDE 8: TIẾN ĐỘ THỰC HIỆN

### **Đã hoàn thành (60%):**
- ✅ Setup môi trường phát triển
- ✅ Thiết kế database schema
- ✅ Xây dựng Model (Car class)
- ✅ Xây dựng DAO layer
- ✅ Xây dựng Controller (Servlets)
- ✅ Tạo giao diện trang chủ
- ✅ Tạo trang danh sách xe
- ✅ Tạo trang chi tiết xe
- ✅ Tạo form đăng bán xe
- ✅ Xử lý upload file
- ✅ Styling và responsive design
- ✅ Kết nối và truy vấn database

### **Tổng số:**
- **Files Java:** 4 files
- **JSP/HTML:** 7 files
- **CSS:** 5 files
- **Database tables:** 1 table

---

## SLIDE 9: ĐIỂM NỔI BẬT

### **1. Kiến Trúc Rõ Ràng**
- Áp dụng mô hình MVC
- Tách biệt rõ ràng giữa Model, View, Controller
- Code dễ bảo trì và mở rộng

### **2. An Toàn**
- Sử dụng PreparedStatement chống SQL Injection
- Xử lý exception đầy đủ
- Validation dữ liệu đầu vào

### **3. Hiệu Năng**
- Connection pooling ready
- Optimized database queries
- Clean code structure

### **4. Trải Nghiệm Người Dùng**
- Giao diện đẹp, hiện đại
- Responsive design
- Loading và error handling tốt

---


## SLIDE 10: KẾT LUẬN

### **Tổng kết:**
- ✅ Dự án đã hoàn thành các chức năng cơ bản
- ✅ Website có thể hoạt động và demo được
- ✅ Code clean, tuân thủ best practices
- ✅ Giao diện đẹp, user-friendly

### **Công nghệ nắm vững:**
- Java Web Development (JSP/Servlet)
- MySQL Database
- MVC Pattern
- JDBC

### **Kinh nghiệm thu được:**
- Xây dựng web application từ đầu
- Làm việc với database
- Xử lý file upload
- Thiết kế giao diện responsive

## PHỤ LỤC: HƯỚNG DẪN CHẠY DỰ ÁN

### **Yêu cầu hệ thống:**
- Java JDK 17+
- Apache Tomcat 9.0
- MySQL 8.0+
- IDE: Eclipse/IntelliJ IDEA

### **Các bước setup:**
1. Import project vào IDE
2. Cấu hình Tomcat server
3. Tạo database: chạy file `database_setup.sql`
4. Cấu hình connection string trong code (nếu cần)
5. Deploy và start server
6. Truy cập: `http://localhost:8080/Car-Store/`

