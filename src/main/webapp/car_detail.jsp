<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Car" %>
<%
    Car car = (Car) request.getAttribute("car");
    if (car == null) {
        response.sendRedirect("home.jsp?error=notfound");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title><%= car.getBrand() %> <%= car.getModel() %> - Car Store</title>
    <link rel="stylesheet" href="css/Styles.css" />
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;800&display=swap"
      rel="stylesheet"
    />
    
</head>
<body>
    <!-- Header -->
    <header class="site-header">
      <div class="container header-inner">
        <div class="brand">
          <div class="logo">Car Store</div>
          <nav class="nav">
            <a href="index.html">Trang chủ</a>
            <a href="home.jsp">Mua xe</a>
            <a href="sellCar.html">Đăng bán</a>
            <a href="gioi-thieu.html">Giới thiệu</a>
            <a href="news.html">Tin tức</a>
          </nav>
        </div>

        <div class="header-actions">
          <button class="btn btn-primary">Tìm xe</button>
          <button class="btn btn-ghost">Hà Nội</button>
        </div>
      </div>
    </header>

    <!-- Main content -->
    <main class="car-detail-container">
        <div class="car-detail-grid">
            <!-- Image Section -->
            <div class="car-image-section">
                <img src="<%= car.getImage() != null && car.getImage().startsWith("image/") ? car.getImage() : "image/" + car.getImage() %>" 
                     alt="<%= car.getBrand() %> <%= car.getModel() %>"
                     onerror="this.src='image/logo.png'">
            </div>

            <!-- Info Section -->
            <div class="car-info-section">
                <h1 class="car-title"><%= car.getBrand() %> <%= car.getModel() %></h1>
                <p class="car-subtitle">Năm <%= car.getYear() %> • <%= String.format("%,d", car.getMileage()) %> km</p>
                
                <div class="price-large"><%= String.format("%,d", car.getPrice()) %> ₫</div>

                <div class="info-row">
                    <span class="info-label">Thương hiệu:</span>
                    <span class="info-value"><%= car.getBrand() %></span>
                </div>
                <div class="info-row">
                    <span class="info-label">Mẫu xe:</span>
                    <span class="info-value"><%= car.getModel() %></span>
                </div>
                <div class="info-row">
                    <span class="info-label">Năm sản xuất:</span>
                    <span class="info-value"><%= car.getYear() %></span>
                </div>
                <div class="info-row">
                    <span class="info-label">Số km đã đi:</span>
                    <span class="info-value"><%= String.format("%,d", car.getMileage()) %> km</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Địa điểm:</span>
                    <span class="info-value"><%= car.getLocation() %></span>
                </div>

                <div class="action-buttons">
                    <button class="btn btn-primary btn-large">Liên hệ người bán</button>
                    <button class="btn btn-ghost btn-large">Yêu thích</button>
                </div>
            </div>
        </div>

        <!-- Description Section -->
        <div class="description-section">
            <h2>Mô tả chi tiết</h2>
            <p><%= car.getDescription() != null && !car.getDescription().isEmpty() ? car.getDescription() : "Chưa có mô tả chi tiết về xe này." %></p>
        </div>

        <!-- Back button -->
        <div style="text-align: center; margin-bottom: 40px;">
            <a href="home.jsp" class="btn btn-ghost">← Quay lại danh sách</a>
        </div>
    </main>

    <!-- Footer -->
    <footer class="site-footer">
      <div class="container footer-inner">
        <div class="brand-col">
          <div class="logo">Car Store</div>
          <p class="muted">Chợ ô tô tin cậy — Kết nối người mua & người bán</p>
        </div>
        <div class="links-col">
          <h4>Về chúng tôi</h4>
          <ul>
            <li><a href="gioi-thieu.html">Giới thiệu</a></li>
            <li><a href="#contact">Liên hệ</a></li>
          </ul>
        </div>
        <div class="contact-col">
          <h4>Hỗ trợ</h4>
          <p class="muted">hotline: 1900 0000</p>
        </div>
      </div>
      <div class="footer-bottom">
        <div class="container">
          © 2025 AutoSieuLuot. Bản quyền thuộc về AutoSieuLuot.
        </div>
      </div>
    </footer>
</body>
</html>
