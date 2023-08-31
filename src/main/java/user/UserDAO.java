package user;

import common.JDBCUtil;

import javax.servlet.ServletContext;

public class UserDAO extends JDBCUtil {
    private static final String LOGIN = " SELECT * FROM user WHERE userID =? AND userPW =? ";

    public UserDAO(ServletContext application) {
        super(application);
    }

    public UserDTO login(UserDTO userDTO) {
        try {
            psmt = con.prepareStatement(LOGIN);
            psmt.setString(1, userDTO.getUserID());
            psmt.setString(2, userDTO.getUserPW());
            rs = psmt.executeQuery();

            if(rs.next()) {
                userDTO.setUserID(rs.getString(1));
                userDTO.setUserPW(rs.getString(2));
                userDTO.setUserName(rs.getString(3));
                userDTO.setUserEmail(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return userDTO;
    }
}
