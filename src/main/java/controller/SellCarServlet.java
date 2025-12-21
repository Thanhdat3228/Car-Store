package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.sql.*;

@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5)
public class SellCarServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String id = request.getParameter("id");
    String brand = request.getParameter("brand");
    String model = request.getParameter("model");
    int year = Integer.parseInt(request.getParameter("year"));
    int km = Integer.parseInt(request.getParameter("km"));
    long price = Long.parseLong(request.getParameter("price"));
    String location = request.getParameter("location");
    String description = request.getParameter("description");

    Part imagePart = request.getPart("image");
    String imageName = imagePart.getSubmittedFileName();
    String imagePath = "images/" + imageName;
    String uploadPath = getServletContext().getRealPath("/") + imagePath;
    imagePart.write(uploadPath);

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_store", "root", "");

      String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, id);
      stmt.setString(2, brand);
      stmt.setString(3, model);
      stmt.setInt(4, year);
      stmt.setInt(5, km);
      stmt.setLong(6, price);
      stmt.setString(7, location);
      stmt.setString(8, description);
      stmt.setString(9, imagePath);

      stmt.executeUpdate();
      conn.close();

      response.sendRedirect("home.jsp");
    } catch (Exception e) {
      e.printStackTrace();
      response.getWriter().println("Lỗi: " + e.getMessage());
    }
  }
}
