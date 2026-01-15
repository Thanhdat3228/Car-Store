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
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		// Kiểm tra xem đã đăng nhập chưa
		if (session != null && session.getAttribute("user") != null) {
			chain.doFilter(request, response); // Cho phép đi tiếp
		} else {
			// Nếu chưa đăng nhập thì chuyển hướng về login.jsp
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}

}
