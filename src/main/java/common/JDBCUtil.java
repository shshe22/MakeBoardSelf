package common;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {

    public Connection con;
    public PreparedStatement psmt;
    public ResultSet rs;

    public JDBCUtil(ServletContext application) {

        try {
            String driver = application.getInitParameter("MYSQL_DRIVER");
            Class.forName(driver);

            String url = application.getInitParameter("MYSQL_URL");
            String id = application.getInitParameter("MYSQL_ID");
            String pw = application.getInitParameter("MYSQL_PW");
            con = DriverManager.getConnection(url, id, pw);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }

            if(psmt != null) {
                psmt.close();
            }

            if(con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
