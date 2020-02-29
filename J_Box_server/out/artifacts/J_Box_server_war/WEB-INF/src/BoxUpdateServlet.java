import Classes.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BoxUpdateServlet extends HttpServlet {
    private  static DBConnector connector = new DBConnector();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        if (!connector.isOpen()) connector = new DBConnector();
        switch (req.getParameter("act")) {
            case "setuser":
                connector.executeUpdate("UPDATE box SET userId = '" +
                        req.getParameter("userId")+"', startDate = '"+req.getParameter("startDate")+"' , " +
                        "endDate = '"+req.getParameter("endDate")+"', location = '"+req.getParameter("location")+"' " +
                        "WHERE id=" + req.getParameter("id"));
                break;
        }
    }
}
