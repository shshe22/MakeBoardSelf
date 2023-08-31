package deleteNote;

import board.BoardDAO;
import board.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/note/delete/*")
public class deleteNoteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardID = Integer.parseInt(req.getParameter("boardID"));

        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setBoardID(boardID);
        boardDAO.deleteNote(boardDTO);

        resp.sendRedirect("/board");
    }
}
