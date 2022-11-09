package MysqlJava;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static MysqlJava.MySqlInsertion.con;

@WebServlet("/login")
public class HtmlData extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PreparedStatement st;
        try {
            st = con.prepareStatement("select * from ApiInfoBase");

            CRUD s = new CRUD();
            ResultSet rs = st.executeQuery();
            rs.close();
            st.close();
            con.close();
            response.sendRedirect("loginsuccess.jsp");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}