import Classes.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class BoxListServlet extends HttpServlet {
    private  static DBConnector connector = new DBConnector();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        if(connector == null) connector = new DBConnector();
        if (!connector.isOpen()) connector = new DBConnector();
        try {
            if (req.getParameter("act").equals("0")) {
                ResultSet rs = connector.executeQuery(
                        "SELECT id, pointId, startDate, endDate, userId, typeBox, location FROM box WHERE pointId='"
                                + req.getParameter("box") + "' and userId='X'");
                while (rs.next()) {
                    writer.print(
                            rs.getString("id") + "<!>" +
                                    rs.getString("pointId") + "<!>" +
                                    rs.getString("startDate") + "<!>" +
                                    rs.getString("endDate") + "<!>" +
                                    rs.getString("userId") + "<!>" +
                                    rs.getString("typeBox") + "<!>" +
                                    rs.getString("location") + "<!!>"
                    );
                }
            } else if (req.getParameter("act").equals("1")) {
                ResultSet rs = connector.executeQuery(
                        "SELECT id, pointId, startDate, endDate, userId, typeBox, location FROM box WHERE  userId='"+
                                req.getParameter("user")+"'");
                while (rs.next()) {
                    writer.print(
                            rs.getString("id") + "<!>" +
                                    rs.getString("pointId") + "<!>" +
                                    rs.getString("startDate") + "<!>" +
                                    rs.getString("endDate") + "<!>" +
                                    rs.getString("userId") + "<!>" +
                                    rs.getString("typeBox") + "<!>" +
                                    rs.getString("location") + "<!!>"
                    );
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
