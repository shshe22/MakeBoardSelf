<%--
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
    <form method="post" action="/comment/update/?commentID=${ requestScope.commentDTO.getCommentID() }">
        <table>
            <thead>
            <tr>
                <th>
                    <h2>댓글 수정 페이지</h2>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><textarea placeholder="댓글을 입력하세요." name="comment" maxlength="2048" style="height: 350px;">${ commentDTO.getComment() }</textarea></td>
            </tr>
            <input type="hidden" value="${ commentDTO.getCommentID() }">
            </tbody>
        </table>
        <input type="submit" value="댓글 수정">
        <a href="/board">목록</a>
        <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="/comment/delete/?commentID=${ commentDTO.getCommentID() }">삭제</a>
        <input type="hidden" onclick="location.href='/comment/update/?commentID=${ commentDTO.getCommentID() }'">
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</body>
</html>