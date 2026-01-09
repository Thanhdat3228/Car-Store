package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarDAO;
import model.Car;

@WebServlet("/SearchCarServlet")
public class SearchCarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        CarDAO dao = new CarDAO();

        List<Car> result;
        if (keyword != null && !keyword.trim().isEmpty()) {
            result = dao.searchByName(keyword.trim());
        } else {
            result = dao.getAllCars();
        }
        System.out.println("Keyword: " + keyword);
        System.out.println("Số xe tìm thấy: " + result.size());

        request.setAttribute("carList", result);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}