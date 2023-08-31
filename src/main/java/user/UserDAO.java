package user;

import common.JDBCUtil;

import javax.servlet.ServletContext;

public class UserDAO extends JDBCUtil {
    private static final String JOIN = " INSERT INTO user VALUES (?, ?, ?, ?) ";
    private static final String LOGIN = " SELECT * FROM user WHERE userID =? AND userPW =? ";

    public UserDAO(ServletContext application) {
        super(application);
    }

    public void join(UserDTO userDTO) {
        try {
            psmt = con.prepareStatement(JOIN);

            psmt.setString(1, userDTO.getUserID());
            psmt.setString(2, userDTO.getUserPW());
            psmt.setString(3, userDTO.getUserName());
            psmt.setString(4, userDTO.getUserEmail());

            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public int login(String userID, String userPW) {
        try {
            psmt = con.prepareStatement(LOGIN);
            psmt.setString(1, userID);
            psmt.setString(2, userPW);
            rs = psmt.executeQuery();

            if(rs.next()) {
                if (rs.getString(1).equals(userID) && rs.getString(2).equals(userPW))
                    return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return 0;
    }
}
