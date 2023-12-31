<%@ page import="comment.CommentDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-30
  Time: 오전 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>게시판</title>
</head>
<body>
<header>
    <h1>게시판</h1>
    <a href="/logout">로그아웃</a>
</header>
<div>
    <table>
        <thead>
        <tr>
            <th>
                <h2>게시글 자세히 보기</h2>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>제목</td>
            <td>${ requestScope.boardDTO.getNoteTitle() }</td>
        </tr>
        <tr>
            <td>작성자</td>
            <td>${ requestScope.boardDTO.getUserID() }</td>
        </tr>
        <tr>
            <td>작성일</td>
            <td>${ requestScope.boardDTO.getDate() }</td>
        </tr>
        <tr>
            <td>내용</td>
            <td>${ requestScope.boardDTO.getNoteContent() }</td>
        </tr>
        </tbody>
    </table>
    <a href="/board">목록</a>
    <a href="/note/update/?boardID=${ requestScope.boardDTO.getBoardID() }">수정</a>
    <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="/note/delete/?boardID=${ requestScope.boardDTO.getBoardID() }">삭제</a>
</div>
<div>
    <table>
        <thead>
        <tr>
            <th>댓글 작성자</th>
            <th>댓글 내용</th>
        </tr>
        </thead>
        <tbody>
        <% for(CommentDTO commentList : (ArrayList<CommentDTO>) request.getAttribute("commentList")) { %>
        <tr>
            <td><%= commentList.getUserID() %></td>
            <td><%= commentList.getComment() %></td>
            <td>
                <a href="/comment/update/?commentID=<%= commentList.getCommentID() %>">댓글 수정</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<div>
    <form method="post" action="/comment">
        * ${ sessionScope.get("userID") } *
        <input type="text" name="comment" placeholder="댓글을 입력하세요.">
        <input type="submit" value="댓글 등록">
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</body>
</html>
