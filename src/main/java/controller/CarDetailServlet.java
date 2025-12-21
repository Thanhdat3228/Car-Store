package controller;

import java.io.IOException;

import dao.CarDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Car;

@WebServlet("/CarDetailServlet")
public class CarDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			// Lấy id từ parameter
			String idParam = request.getParameter("id");
			if (idParam == null || idParam.isEmpty()) {
				response.sendRedirect("home.jsp");
				return;
			}

			int id = Integer.parseInt(idParam);
			CarDAO dao = new CarDAO();
			Car car = dao.getCarById(id);

			// Kiểm tra nếu không tìm thấy xe
			if (car == null) {
				response.sendRedirect("home.jsp?error=notfound");
				return;
			}

			request.setAttribute("car", car);
			RequestDispatcher rd = request.getRequestDispatcher("car_detail.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			// ID không hợp lệ
			response.sendRedirect("home.jsp?error=invalid");
		} catch (Exception e) {
			// Lỗi khác
			e.printStackTrace();
			response.sendRedirect("home.jsp?error=database");
		}
	}
}