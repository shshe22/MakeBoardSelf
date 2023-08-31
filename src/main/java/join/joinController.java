package join;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import user.UserDAO;
import user.UserDTO;

@WebServlet("/join")
public class joinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/join/join.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String userID = req.getParameter("userID");
        String userPW = req.getParameter("userPW");
        String userName = req.getParameter("userName");
        String userEmail = req.getParameter("userEmail");

        UserDAO userDAO = new UserDAO(this.getServletContext());
        UserDTO userDTO = new UserDTO();

        userDTO.setUserID(userID);
        userDTO.setUserPW(userPW);
        userDTO.setUserName(userName);
        userDTO.setUserEmail(userEmail);

        userDAO.join(userDTO);

        resp.sendRedirect("/login");
    }
}
