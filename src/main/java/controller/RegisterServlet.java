package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegisterServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String phoneNumber=request.getParameter("phoneNumber");

	        try (Connection conn = DBConnection.getConnection()) {
	            PreparedStatement ps = conn.prepareStatement(
	                "INSERT INTO users(username, password, phoneNumber, role) VALUES (?, ?, ?, 'user')");
	            ps.setString(1, username);
	            ps.setString(2, password);
	            ps.setString(3, phoneNumber);
	            ps.executeUpdate();

	            response.sendRedirect("login.jsp?registered=1");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect("login.jsp?error=2");
	        }
	    }

}
