package comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/comment")
public class CommentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String userID = (String) session.getAttribute("userID");
        String comment = req.getParameter("comment");
        int boardID = (int) session.getAttribute("boardID");

        CommentDAO commentDAO = new CommentDAO(this.getServletContext());
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setBoardID(boardID);
        commentDTO.setUserID(userID);
        commentDTO.setComment(comment);
        commentDTO.setCommentID(commentDAO.getNum());

        commentDAO.createComment(commentDTO);

        PrintWriter script = resp.getWriter();
        script.println("<script>");
        script.println("alert('댓글이 등록되었습니다.')");
        script.println("history.back()"); // 이전 페이지로 다시 돌려놓는 작업
        script.println("</script>");
    }
}
