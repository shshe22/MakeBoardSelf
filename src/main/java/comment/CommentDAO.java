package comment;

import common.JDBCUtil;

import javax.servlet.ServletContext;
import java.util.ArrayList;

public class CommentDAO extends JDBCUtil {
    private static final String GET_NUM = " SELECT commentID FROM comment ORDER BY commentID DESC ";
    private static final String COMMENT_LIST = " SELECT userID, comment, commentID FROM comment WHERE boardID =? ";
    private static final String CREATE_COMMENT = " INSERT INTO comment VALUES (?, ?, ?, ?) ";
    private static final String DELETE_COMMENT = " DELETE FROM comment WHERE commentID = ? ";
    private static final String GET_COMMENT = " SELECT * FROM comment WHERE commentID = ? ";
    private static final String UPDATE_COMMENT = " UPDATE comment SET boardID = ?, userID = ?, comment = ? WHERE commentID = ? ";

    public CommentDAO(ServletContext application) {
        super(application);
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

    public ArrayList<CommentDTO> commentList(int boardID) {
        ArrayList<CommentDTO> commentList = new ArrayList<>();

        try {
            psmt = con.prepareStatement(COMMENT_LIST);
            psmt.setInt(1, boardID);
            rs = psmt.executeQuery();

            while (rs.next()) {
                CommentDTO commentDTO = new CommentDTO();

                commentDTO.setUserID(rs.getString(1));
                commentDTO.setComment(rs.getString(2));
                commentDTO.setCommentID(rs.getInt(3));
                commentDTO.setBoardID(boardID);

                commentList.add(commentDTO);
            }
        } catch (Exception e) {

        } finally {
            close();
        }

        return commentList;
    }

    public void createComment(CommentDTO commentDTO) {
        try {
            psmt = con.prepareStatement(CREATE_COMMENT);

            psmt.setInt(1, commentDTO.getBoardID());
            psmt.setString(2, commentDTO.getUserID());
            psmt.setString(3, commentDTO.getComment());
            psmt.setInt(4, commentDTO.getCommentID());

            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void deleteComment(CommentDTO commentDTO) {
        try {
            psmt = con.prepareStatement(DELETE_COMMENT);
            psmt.setInt(1, commentDTO.getCommentID());
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public CommentDTO getComment(int commentID) {
        CommentDTO commentDTO = new CommentDTO();
        try {
            psmt = con.prepareStatement(GET_COMMENT);
            psmt.setInt(1, commentID);
            rs = psmt.executeQuery();

            if (rs.next()) {
                commentDTO.setBoardID(rs.getInt(1));
                commentDTO.setUserID(rs.getString(2));
                commentDTO.setComment(rs.getString(3));
                commentDTO.setCommentID(rs.getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return commentDTO;
    }

    public void updateComment(CommentDTO commentDTO) {
        try {
            psmt = con.prepareStatement(UPDATE_COMMENT);

            psmt.setInt(1, commentDTO.getBoardID());
            psmt.setString(2, commentDTO.getUserID());
            psmt.setString(3, commentDTO.getComment());
            psmt.setInt(4, commentDTO.getCommentID());

            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
