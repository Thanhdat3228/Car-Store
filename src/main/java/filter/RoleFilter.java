package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/sellCar.html")
public class RoleFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session=req.getSession(false);
		
		String role = (session != null) ? (String) session.getAttribute("role") : null;
		System.out.println("Role trong session: " + role);
		
		if("admin".equals(role)) {
			chain.doFilter(request, response);
		}else {
			res.sendRedirect(req.getContextPath() + "/access-denied.jsp"); // Từ chối nếu không phải admin

		}
	}
	
	
}
