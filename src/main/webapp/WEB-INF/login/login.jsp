<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-31
  Time: 오전 10:34
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
    <a href="/join">회원가입</a>
</header>
<div>
    <div>
        <form method="post" action="/login">
            <table>
                <thead>
                <tr><th><h2>로그인</h2></th></tr></thead>
                <tbody>
                <tr>
                    <td>
                        <input type="text" name="userID" placeholder="아이디를 입력해주세요">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="userPW" placeholder="비밀번호를 입력해주세요.">
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="로그인">
        </form>
    </div>
</div>
</body>
</html>
