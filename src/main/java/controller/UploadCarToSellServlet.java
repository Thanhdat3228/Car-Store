package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.DBConnection;

@WebServlet("/UploadCarToSellServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UploadCarToSellServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "image"; // thư mục lưu ảnh trong webapp

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String id = request.getParameter("id");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        int mileage = Integer.parseInt(request.getParameter("mileage"));
        int price = Integer.parseInt(request.getParameter("price"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");

        // Xử lý file upload
        Part imagePart = request.getPart("image");
        String imageName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

        // Đường dẫn tuyệt đối tới thư mục /image trong webapp
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

        // Lưu file ảnh vào thư mục image
        imagePart.write(uploadPath + File.separator + imageName);

        // Đường dẫn ảnh để lưu vào DB (tương đối)
        String imagePath = UPLOAD_DIR + "/" + imageName;

        // Lưu dữ liệu vào database
        try {
            Connection conn = DBConnection.getConnection(); // bạn đã có class DBConnection
            String sql = "INSERT INTO cars (id, brand, model, year, mileage, price, location, description, image) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, brand);
            stmt.setString(3, model);
            stmt.setInt(4, year);
            stmt.setInt(5, mileage);
            stmt.setInt(6, price);
            stmt.setString(7, location);
            stmt.setString(8, description);
            stmt.setString(9, imagePath); // lưu đường dẫn ảnh
            stmt.executeUpdate();
            
         // 2. Lấy dữ liệu thông số kỹ thuật từ form
            int seat_Count = Integer.parseInt(request.getParameter("seat_Count"));
            String dimensions = request.getParameter("dimensions");
            String wheelType = request.getParameter("wheels");
            String weight = request.getParameter("weight");
            int ground_clearance = Integer.parseInt(request.getParameter("ground_clearance"));
            String engine = request.getParameter("engine");
            String fuelType = request.getParameter("fuel_type");

            // 3. Insert vào bảng car_specs
            String sqlSpec = "INSERT INTO car_specs (car_id, seat_count, dimensions, wheel_type, weight, ground_clearance, engine, fuel_type) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement specStmt = conn.prepareStatement(sqlSpec);
            specStmt.setString(1, id);
            specStmt.setInt(2, seat_Count); // nếu form luôn nhập số
            specStmt.setString(3, dimensions);
            specStmt.setString(4, wheelType);
            specStmt.setString(5, weight);
            specStmt.setInt(6, ground_clearance);
            specStmt.setString(7, engine);
            specStmt.setString(8, fuelType);
            specStmt.executeUpdate();

            conn.close();

            response.sendRedirect("success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}