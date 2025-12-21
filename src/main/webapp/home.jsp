<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Danh sách xe - Car Store</title>
<link rel="stylesheet" href="css/Styles.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;800&display=swap"
	rel="stylesheet" />
</head>
<body>
	<!-- Header -->
	<header class="site-header">
		<div class="container header-inner">
			<div class="brand">
				<div class="logo">Car Store</div>
				<nav class="nav">
					<a href="index.html">Trang Chủ</a> <a href="home.jsp">Mua xe</a> <a
						href="sellCar.html">Đăng bán</a> <a href="gioi-thieu.html">Giới
						thiệu</a> <a href="news.html">Tin tức</a>
				</nav>
			</div>

			<div class="header-actions">
				<button class="btn btn-primary">Tìm xe</button>
				<button class="btn btn-ghost">Hà Nội</button>
			</div>
		</div>
	</header>

	<!-- Main content -->
	<main class="container" style="padding: 40px 20px;">
		<div style="margin-bottom: 30px;">
			<h1
				style="color: var(--primary); font-size: 32px; font-weight: 800; margin-bottom: 10px;">Danh
				sách xe đã đăng</h1>
			<p class="muted">Tất cả các xe đang được rao bán</p>
		</div>

		<%
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_store", "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cars");

			boolean hasData = false;
			// Lưu dữ liệu vào list trước
			java.util.List<java.util.Map<String, Object>> carList = new java.util.ArrayList<>();
			while (rs.next()) {
				hasData = true;
				java.util.Map<String, Object> car = new java.util.HashMap<>();
				car.put("id", rs.getInt("id"));
				car.put("image", rs.getString("image"));
				car.put("brand", rs.getString("brand"));
				car.put("model", rs.getString("model"));
				car.put("year", rs.getInt("year"));
				car.put("price", rs.getLong("price"));
				car.put("km", rs.getInt("km"));
				car.put("location", rs.getString("location"));
				carList.add(car);
			}

			if (hasData) {
		%>
		<div class="grid-cards">
			<%
			for (java.util.Map<String, Object> car : carList) {
				String image = (String) car.get("image");
				String brand = (String) car.get("brand");
				String model = (String) car.get("model");
				int year = (Integer) car.get("year");
				long price = (Long) car.get("price");
				int km = (Integer) car.get("km");
				String location = (String) car.get("location");
				int id = (Integer) car.get("id");
			%>
			<article class="card car-card">
				<a href="CarDetailServlet?id=<%=id%>"
					style="text-decoration: none; color: inherit;">
					<div class="card-media">
						<img
							src="<%=image != null && image.startsWith("image/") ? image : "image/" + image%>"
							alt="<%=brand%> <%=model%>" onerror="this.src='image/logo.png'">
						<button class="fav" aria-label="Yêu thích">♥</button>
					</div>
					<div class="card-body">
						<h4><%=brand%>
							<%=model%></h4>
						<p class="muted"><%=year%>
							•
							<%=String.format("%,d", km)%>
							km
						</p>
						<div class="card-footer">
							<div class="price"><%=String.format("%,d", price)%>
								₫
							</div>
							<div class="location"><%=location%></div>
						</div>
					</div>
				</a>
			</article>
			<%
			}
			%>
		</div>
		<%
		} else {
		%>
		<div
			style="text-align: center; padding: 60px 20px; background: var(--bg-white); border-radius: var(--radius); box-shadow: var(--shadow-md);">
			<p class="muted" style="font-size: 18px; margin-bottom: 20px;">Chưa
				có xe nào được đăng bán.</p>
			<a href="sellCar.html" class="btn btn-primary"
				style="margin-top: 20px; display: inline-block;">Đăng bán xe
				ngay</a>
		</div>
		<%
		}
		conn.close();
		} catch (ClassNotFoundException e) {
		%>
		<div
			style="text-align: center; padding: 60px 20px; background: var(--bg-white); border-radius: var(--radius); box-shadow: var(--shadow-md);">
			<h3 style="color: var(--primary); margin-bottom: 15px;">⚠️ Chưa
				cài đặt MySQL Driver</h3>
			<p class="muted" style="margin-bottom: 20px;">Vui lòng thêm MySQL
				Connector/J vào project.</p>
			<p style="color: var(--text-muted); font-size: 14px;">
				Tải tại: <a href="https://dev.mysql.com/downloads/connector/j/"
					target="_blank">MySQL Connector/J</a>
			</p>
		</div>
		<%
		} catch (SQLException e) {
		%>
		<div
			style="text-align: center; padding: 60px 20px; background: var(--bg-white); border-radius: var(--radius); box-shadow: var(--shadow-md);">
			<h3 style="color: var(--primary); margin-bottom: 15px;">⚠️ Lỗi
				kết nối Database</h3>
			<p class="muted" style="margin-bottom: 10px;">Không thể kết nối
				đến database.</p>
			<p
				style="color: var(--text-muted); font-size: 14px; margin-bottom: 20px;">Vui
				lòng kiểm tra:</p>
			<ul
				style="text-align: left; display: inline-block; color: var(--text-muted); font-size: 14px;">
				<li>MySQL đã được cài đặt và đang chạy</li>
				<li>Database 'car_store' đã được tạo</li>
				<li>User và password đúng</li>
				<li>Đã chạy file <code>database_setup.sql</code></li>
			</ul>
			<p
				style="margin-top: 20px; font-size: 12px; color: var(--text-muted);">
				Chi tiết:
				<%=e.getMessage()%></p>
		</div>
		<%
		} catch (Exception e) {
		%>
		<div
			style="text-align: center; padding: 60px 20px; background: var(--bg-white); border-radius: var(--radius); box-shadow: var(--shadow-md);">
			<h3 style="color: var(--primary); margin-bottom: 15px;">⚠️ Lỗi</h3>
			<p class="muted"><%=e.getMessage()%></p>
		</div>
		<%
		}
		%>
	</main>

	<!-- Footer -->
	<footer class="site-footer">
		<div class="container footer-inner">
			<div class="brand-col">
				<div class="logo">Car Store</div>
				<p class="muted">Chợ ô tô tin cậy — Kết nối người mua & người
					bán</p>
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
			<div class="container">© 2025 AutoSieuLuot. Bản quyền thuộc về
				AutoSieuLuot.</div>
		</div>
	</footer>
</body>
</html>
