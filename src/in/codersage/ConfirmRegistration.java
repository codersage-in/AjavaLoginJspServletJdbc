package in.codersage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmRegistration
 */
@WebServlet("/ConfirmRegistration")
public class ConfirmRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		Connection conn = (Connection) getServletContext().getAttribute("DB_CONN");
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("select * from login where username='"+ email +"' and token='"+ token +"'");
			if(rs.next()) {
				rs.updateBoolean("enabled", true);
				rs.updateRow();
				out.print("<h2> Registration completed !!! </h2>");
				rs.close();
			} else {
				out.print("<h2> Wrong infromation !!! </h2>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
