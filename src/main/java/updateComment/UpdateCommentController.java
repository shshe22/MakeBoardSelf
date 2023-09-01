package updateComment;

import board.BoardDAO;
import board.BoardDTO;
import comment.CommentDAO;
import comment.CommentDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/comment/update/*")
public class UpdateCommentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int commentID = Integer.parseInt(req.getParameter("commentID"));

        CommentDAO commentDAO = new CommentDAO(this.getServletContext());
        CommentDTO commentDTO = commentDAO.getComment(commentID);

        HttpSession session = req.getSession();
        String userID = (String) session.getAttribute("userID");
        session.setAttribute("userID", userID);

        if(userID.equals(commentDTO.getUserID())){
            req.setAttribute("commentDTO", commentDTO);
            req.getRequestDispatcher("/WEB-INF/update/updateComment.jsp").forward(req, resp);
        } else {
            PrintWriter script = resp.getWriter();
            script.println("<script>");
            script.println("alert('권한이 없습니다.')");
            script.println("history.back()"); // 이전 페이지로 다시 돌려놓는 작업
            script.println("</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String userID = (String) session.getAttribute("userID");
        session.setAttribute("userID", userID);
        int boardID = (int) session.getAttribute("boardID");
        session.setAttribute("boardID", boardID);

        int commentID = Integer.parseInt(req.getParameter("commentID"));
        String comment = req.getParameter("comment");

        CommentDAO commentDAO = new CommentDAO(this.getServletContext());
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setUserID(userID);
        commentDTO.setBoardID(boardID);
        commentDTO.setCommentID(commentID);
        commentDTO.setComment(comment);

        commentDAO.updateComment(commentDTO);
        resp.sendRedirect("/note/?boardID=" + boardID);
    }
}
