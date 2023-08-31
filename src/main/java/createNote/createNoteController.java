package createNote;

import board.BoardDAO;
import board.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/note/create")
public class createNoteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/create/createNote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String userID = (String) session.getAttribute("userID");
        String noteTitle = req.getParameter("noteTitle");
        String noteContent = req.getParameter("noteContent");

        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setUserID(userID);
        boardDTO.setNoteTitle(noteTitle);
        boardDTO.setNoteContent(noteContent);
        boardDTO.setDate(boardDAO.getDate());
        boardDTO.setBoardID(boardDAO.getNum());

        boardDAO.createNote(boardDTO);

        resp.sendRedirect("/board");
    }
}
