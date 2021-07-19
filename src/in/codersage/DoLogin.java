package in.codersage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			String rememberMe = request.getParameter("remember");
			Connection conn = (Connection) getServletContext().getAttribute("DB_CONN");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login where username = '" + request.getParameter("email")
					+ "' and password = '" + PasswordHasher.getHashPass(request.getParameter("psw")) + "'");
			if (rs.next()) {
				out.print("Login Success");
				if(rememberMe.equals("remember")) {
					Cookie email = new Cookie("email", request.getParameter("email"));
					Cookie password = new Cookie("password", request.getParameter("psw"));
					response.addCookie(email);
					response.addCookie(password);
				}
			} else {
				out.print("Login not Success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
