import Classes.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRegistration extends HttpServlet {
    private  static DBConnector connector = new DBConnector();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PrintWriter writer = resp.getWriter();
            System.out.println("get login");
            if(connector == null) connector = new DBConnector();
            if (!connector.isOpen()) connector = new DBConnector();
            if (req.getParameter("act").equals("reg")) {
                long c = connector.executeUpdate("INSERT users VALUE(0, '" +
                        req.getParameter("name") + "','" + req.getParameter("number") + "','" + req.getParameter("pin") + "')");
                writer.print(Long.toString(c));
            } else if (req.getParameter("act").equals("log")) {
                try {
                    ResultSet rs = connector.executeQuery("SELECT id,pin, name FROM users WHERE number='" + req.getParameter("number") + "'");
                    if (rs.next()) {
                        if (rs.getString("pin").equals(req.getParameter("pin"))) {
                            writer.print("ok<!>" + rs.getString("name") + "<!>" + rs.getString("id"));
                        } else {
                            writer.print("invalid pin<!>");
                        }
                    } else {
                        writer.print("no user<!>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}
