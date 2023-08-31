package updateNote;

import board.BoardDAO;
import board.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/note/update/*")
public class updateNoteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardID = Integer.parseInt(req.getParameter("boardID"));

        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        BoardDTO boardDTO = boardDAO.showNote(boardID);

        req.setAttribute("boardDTO", boardDTO);
        req.getRequestDispatcher("/WEB-INF/update/updateNote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int boardID = Integer.parseInt(req.getParameter("boardID"));
        String userID = req.getParameter("userID");
        String noteTitle = req.getParameter("noteTitle");
        String noteContent = req.getParameter("noteContent");

        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setBoardID(boardID);
        boardDTO.setUserID(userID);
        boardDTO.setNoteTitle(noteTitle);
        boardDTO.setNoteContent(noteContent);

        boardDAO.updateNote(boardDTO);

        resp.sendRedirect("/board");
    }
}