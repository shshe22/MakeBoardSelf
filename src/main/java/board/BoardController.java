package board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/board")
public class BoardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO(this.getServletContext());
        ArrayList<BoardDTO> boardList = boardDAO.getList();

        req.setAttribute("boardList", boardList);
        req.getRequestDispatcher("/WEB-INF/board/board.jsp").forward(req, resp);
    }
}
