package deleteNote;

import board.BoardDAO;
import board.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/note/delete/*")
public class DeleteNoteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int boardID = Integer.parseInt(req.getParameter("boardID"));

        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        BoardDTO boardDTO = boardDAO.showNote(boardID);

        HttpSession session = req.getSession();
        String userID = (String) session.getAttribute("userID");
        session.setAttribute("userID", userID);

        if(userID.equals(boardDTO.getUserID())){
            boardDAO.deleteNote(boardDTO);
            resp.sendRedirect("/board");
        } else {
            PrintWriter script = resp.getWriter();
            script.println("<script>");
            script.println("alert('권한이 없습니다.')");
            script.println("history.back()"); // 이전 페이지로 다시 돌려놓는 작업
            script.println("</script>");
        }
    }
}
