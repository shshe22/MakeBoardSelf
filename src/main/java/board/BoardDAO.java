package board;

import javax.servlet.ServletContext;

import common.JDBCUtil;

import java.util.ArrayList;

public class BoardDAO extends JDBCUtil {
    private static final String GET_DATE = " SELECT NOW() ";
    private static final String GET_NUM = " SELECT boardID FROM noteList ORDER BY boardID DESC ";

    private static final String SHOW_NOTE = " SELECT * FROM noteList WHERE boardID = ? ";
    private static final String GET_LIST = " SELECT * FROM noteList ORDER BY boardID DESC ";
    private static final String CREATE_NOTE = " INSERT INTO noteList VALUES (?, ?, ?, ?, ?) ";
    private static final String UPDATE_NOTE = "";
    private static final String DELETE_NOTE = " DELETE FROM notelist WHERE boardID = ? ";

    public BoardDAO(ServletContext application) {
        super(application);
    }

    public String getDate() {
        try {
            psmt = con.prepareStatement(GET_DATE);
            rs = psmt.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getNum() {
        try {
            psmt = con.prepareStatement(GET_NUM);
            rs = psmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1; // 첫 번째 게시물인 경우
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getNum();
    }

    public BoardDTO showNote(int boardID) {
        BoardDTO boardDTO = new BoardDTO();
        try {
            psmt = con.prepareStatement(SHOW_NOTE);
            psmt.setInt(1, boardID);
            rs = psmt.executeQuery();

            if (rs.next()) {
                boardDTO.setBoardID(rs.getInt(1));
                boardDTO.setUserID(rs.getString(2));
                boardDTO.setNoteTitle(rs.getString(3));
                boardDTO.setNoteContent(rs.getString(4));
                boardDTO.setDate(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boardDTO;
    }

    public ArrayList<BoardDTO> getList() {
        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();

        try {
            psmt = con.prepareStatement(GET_LIST);
            rs = psmt.executeQuery();

            while(rs.next()) {
                BoardDTO boardDTO = new BoardDTO();

                boardDTO.setBoardID(rs.getInt(1));
                boardDTO.setUserID(rs.getString(2));
                boardDTO.setNoteTitle(rs.getString(3));
                boardDTO.setNoteContent(rs.getString(4));
                boardDTO.setDate(rs.getString(5));

                boardDTOList.add(boardDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boardDTOList;
    }

    public void createNote(BoardDTO boardDTO) {
        try {
            psmt = con.prepareStatement(CREATE_NOTE);

            psmt.setInt(1, boardDTO.getBoardID());
            psmt.setString(2, boardDTO.getUserID());
            psmt.setString(3, boardDTO.getNoteTitle());
            psmt.setString(4, boardDTO.getNoteContent());
            psmt.setString(5, boardDTO.getDate());

            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNote() {
        try {
            psmt = con.prepareStatement(UPDATE_NOTE);

            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNote(BoardDTO boardDTO) {
        try {
            psmt = con.prepareStatement(DELETE_NOTE);
            psmt.setInt(1, boardDTO.getBoardID());
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
