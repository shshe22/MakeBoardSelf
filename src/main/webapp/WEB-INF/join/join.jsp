<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-31
  Time: 오후 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판</title>
</head>
<body>
<header>
    <h1>게시판</h1>
    <a href="/login">로그인</a>
</header>
<div>
    <form method="post" action="/join">
        <table>
            <thead>
            <tr>
                <th>
                    <h2>회원 가입 페이지</h2>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="text" placeholder="아이디를 입력하세요." name="userID" maxlength="50"></td>
            </tr>
            <tr>
                <td><input type="password" placeholder="비밀번호를 입력하세요." name="userPW" maxlength="50"></td>
            </tr>
            <tr>
                <td><input type="text" placeholder="이름을 입력하세요." name="userName" maxlength="50"></td>
            </tr>
            <tr>
                <td><input type="email" placeholder="이메일을 입력하세요." name="userEmail" maxlength="50"></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="가입하기">
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</body>
</html>
