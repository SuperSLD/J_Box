import Classes.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoxPointList extends HttpServlet {
    private  static DBConnector connector = new DBConnector();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        if (!connector.isOpen()) connector = new DBConnector();

        try {
            ResultSet rs = connector.executeQuery("SELECT id, location, size FROM boxPoint");
            while (rs.next()) {
                writer.print(rs.getString("id")+"<!>"+rs.getString("location")+"<!>"+rs.getString("size")+"<!!>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
