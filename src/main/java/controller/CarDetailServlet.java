package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarDAO;
import model.Car;

@WebServlet("/CarDetailServlet")
public class CarDetailServlet extends HttpServlet {
<<<<<<< HEAD

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect("home.jsp");
                return;
            }

            int id = Integer.parseInt(idParam);
            CarDAO dao = new CarDAO();
            Car car = dao.getCarById(id);

            if (car == null) {
                response.sendRedirect("home.jsp?error=notfound");
                return;
            }

            request.setAttribute("car", car);
            RequestDispatcher rd = request.getRequestDispatcher("car_detail.jsp");
            rd.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("home.jsp?error=invalid");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home.jsp?error=database");
        }
    }
}
=======
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try {
			// Lấy id từ parameter
			String idParam = request.getParameter("id");
			if (idParam == null || idParam.isEmpty()) {
				System.out.println("CarDetailServlet: missing id param");
				response.sendRedirect("home.jsp");
				return;
			}

			int id = Integer.parseInt(idParam);
			System.out.println("CarDetailServlet: id=" + id);
			CarDAO dao = new CarDAO();
			Car car = dao.getCarById(id);
			System.out.println("CarDetailServlet: car=" + car);

			// Kiểm tra nếu không tìm thấy xe
			if (car == null) {
				System.out.println("CarDetailServlet: car not found for id=" + id);
				response.sendRedirect("home.jsp?error=notfound");
				return;
			}

			request.setAttribute("car", car);
			RequestDispatcher rd = request.getRequestDispatcher("car_detail.jsp");
			rd.forward(request, response);
			
		} catch (NumberFormatException e) {
			// ID không hợp lệ
			e.printStackTrace();
			response.sendRedirect("home.jsp?error=invalid");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("home.jsp?error=server");
		}
	}
}
>>>>>>> 29fd000f9abb2caf82b173ca2d97c7e4d0c12a04
