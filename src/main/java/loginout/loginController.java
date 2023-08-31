package loginout;

import board.BoardDAO;
import user.UserDAO;
import user.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class loginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String userID = req.getParameter("userID");
        String userPW = req.getParameter("userPW");

        UserDAO userDAO = new UserDAO(this.getServletContext());
        UserDTO userDTO = new UserDTO();

        userDTO.setUserID(userID);
        userDTO.setUserPW(userPW);

        userDAO.login(userDTO);

//        if(userID.equals() && userPW.equals()){
//            HttpSession session = req.getSession();
//            session.setAttribute("userDTO", userDTO);
//            resp.sendRedirect("/board");
//        } else {
//            PrintWriter script = resp.getWriter();
//            script.println("<script>");
//            script.println("alert('로그인에 실패했습니다.')");
//            script.println("history.back()"); // 이전 페이지로 다시 돌려놓는 작업
//            script.println("</script>");
//        }
    }
}
