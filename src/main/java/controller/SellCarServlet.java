package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet("/SellCarServlet")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5)
public class SellCarServlet extends HttpServlet {
  @Override
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

  }

}
