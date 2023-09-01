package showNote;

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
import java.util.ArrayList;

@WebServlet("/note/*")
public class ShowNoteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardID = Integer.parseInt(req.getParameter("boardID"));

        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        BoardDTO boardDTO = boardDAO.showNote(boardID);

        HttpSession session = req.getSession();
        session.setAttribute("boardID", boardID);

        req.setAttribute("boardDTO", boardDTO);

        CommentDAO commentDAO = new CommentDAO(this.getServletContext());
        ArrayList<CommentDTO> commentList = commentDAO.commentList(boardID);

        req.setAttribute("commentList", commentList);

        req.getRequestDispatcher("/WEB-INF/board/showNote.jsp").forward(req, resp);
    }

}
