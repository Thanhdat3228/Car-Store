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
