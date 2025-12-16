<%@ page import="java.sql.*" %>
<html>
<head>
  <title>Trang chủ</title>
</head>
<body>
  <h2>Danh sách xe đã đăng</h2>
  <%
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_store", "root", "");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM cars");

    while (rs.next()) {
      String image = rs.getString("image");
  %>
    <div style="border:1px solid #ccc; padding:10px; margin:10px">
      <img src="<%= image %>" width="200"><br>
      <strong><%= rs.getString("brand") %> <%= rs.getString("model") %> (<%= rs.getInt("year") %>)</strong><br>
      Giá: <%= rs.getLong("price") %> VNĐ<br>
      Số km: <%= rs.getInt("km") %> km<br>
      Vị trí: <%= rs.getString("location") %><br>
      Mô tả: <%= rs.getString("description") %><br>
    </div>
  <%
    }
    conn.close();
  %>
</body>
</html>
