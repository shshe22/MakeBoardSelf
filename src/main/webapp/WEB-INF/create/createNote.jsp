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
</header>
<div>
    <form method="post" action="/note/create">
        <table>
            <thead>
            <tr>
                <th>
                    <h2>새로운 게시글 등록 페이지</h2>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="text" placeholder="닉네임을 입력하세요." name="userID" maxlength="50"></td>
            </tr>
            <tr>
                <td><input type="text" placeholder="제목을 입력하세요." name="noteTitle" maxlength="50"></td>
            </tr>
            <tr>
                <td><textarea placeholder="내용을 입력하세요." name="noteContent" maxlength="2048" style="height: 350px;"></textarea></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="등록">
    </form>
    <a href="/board">목록</a>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</body>
</html>
