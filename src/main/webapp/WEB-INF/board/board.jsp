<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-30
  Time: 오전 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="board.BoardDTO" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>게시판</title>
</head>
<body>
<header>
    <h1>게시판</h1>
</header>
<div>
    <table>
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <% for(BoardDTO boardList : (ArrayList<BoardDTO>) request.getAttribute("boardList")) { %>
        <tr onclick="location.href='/note/?boardID=<%= boardList.getBoardID() %>'">
            <td><%= boardList.getBoardID() %></td>
            <td><%= boardList.getNoteTitle()%></td>
            <td><%= boardList.getUserID()%></td>
            <td><%= boardList.getDate()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <a href="/note/create">글쓰기</a>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</body>
</html>
