<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Car" %>
<%
    Car car = (Car) request.getAttribute("car");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><%= car.getBrand() %> <%= car.getModel() %></title>
</head>
<body>
    <h1><%= car.getBrand() %> <%= car.getModel() %></h1>
    <img src="images/<%= car.getImage() %>" alt="<%= car.getModel() %>" style="max-width:400px;">
    <p>Năm: <%= car.getYear() %></p>
    <p>Số km: <%= car.getMileage() %> km</p>
    <p>Giá: <%= car.getPrice() %> ₫</p>
    <p>Địa điểm: <%= car.getLocation() %></p>
    <p>Mô tả: <%= car.getDescription() %></p>
    <a href="index.jsp">Quay lại danh sách</a>
</body>
</html>