package showNote;

import board.BoardDAO;
import board.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/note/*")
public class showNoteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardID = Integer.parseInt(req.getParameter("boardID"));

        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        BoardDTO boardDTO = boardDAO.showNote(boardID);

        req.setAttribute("boardDTO", boardDTO);
        req.getRequestDispatcher("/WEB-INF/board/showNote.jsp").forward(req, resp);
    }
}
