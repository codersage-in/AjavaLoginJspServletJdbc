package in.codersage;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoRegistration
 */
@WebServlet("/DoRegistration")
public class DoRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = UUID.randomUUID().toString();
		PrintWriter out = response.getWriter();
		Connection conn = (Connection) getServletContext().getAttribute("DB_CONN");
		try {
			PreparedStatement preStmt = conn
					.prepareStatement("INSERT INTO login (username, password, token) values (?,?,?)");
			preStmt.setString(1, request.getParameter("email"));
			preStmt.setString(2, PasswordHasher.getHashPass(request.getParameter("psw")));
			preStmt.setString(3, token);
			preStmt.executeUpdate();
		} catch (SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("email");

		MailSender.send(email, token, getServletContext().getContextPath(), "ConfirmRegistration");
		out.print(
				"<h2 style='align:center'>Mail Sent : To activate your account, click on the link given in the mail.</h2>");
	}

}
