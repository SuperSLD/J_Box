package Classes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class CarsServlet extends HttpServlet {
    private  static DBConnector connector = new DBConnector();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        if(connector == null) connector = new DBConnector();
        if (!connector.isOpen()) connector = new DBConnector();
        if (req.getParameter("act").equals("add")) {
            String SQL = "INSERT cars VALUE(0, '"+req.getParameter("location")+"'," +
                    "'"+req.getParameter("code")+"'," +
                    "'"+req.getParameter("date")+"'," +
                    "'"+req.getParameter("start")+"'," +
                    "'"+req.getParameter("userId")+"')";
            System.out.println(SQL);
            connector.executeUpdate(SQL);
        }
        if (req.getParameter("act").equals("get")) {
            try {
                ResultSet rs = connector.executeQuery("SELECT code, location, start FROM cars WHERE userId='"+req.getParameter("userId")+"'");
                while (rs.next()) {
                    writer.print(rs.getString("code")+"<!>"+rs.getString("location")+"<!>"+rs.getString("start")+"<!!>");
                }
            } catch (Exception ex) {
            }
        }
    }
}
