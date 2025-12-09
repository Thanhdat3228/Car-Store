package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarDAO;
import model.Car;

public class CarDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		CarDAO dao=new CarDAO();
		Car car=dao.getCarById(id);

		request.setAttribute("car", car);
		RequestDispatcher rd=request.getRequestDispatcher("car_detail.jsp");
		rd.forward(request, response);
	}
}
