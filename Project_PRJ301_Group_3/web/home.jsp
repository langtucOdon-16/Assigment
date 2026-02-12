//Test của GiangLT sau sẽ thay bằng home của NamTQ

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background: #f5f5f5;
        }

        .header {
            background: #fff;
            padding: 15px 30px;
            border-bottom: 1px solid #ddd;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            font-size: 22px;
            font-weight: bold;
            color: #f4602a;
        }

        .user-menu a {
            margin-left: 15px;
            text-decoration: none;
            color: #333;
            font-weight: 600;
        }

        .user-menu a:hover {
            color: #f4602a;
        }

        .content {
            padding: 30px;
        }
    </style>
</head>
<body>

<!-- ===== HEADER ===== -->
<div class="header">
    <div class="logo">
        PRJ301 Shop
    </div>

    <div class="user-menu">

        <c:if test="${empty sessionScope.account}">
            <a href="login.jsp">Đăng nhập</a>
            <a href="register.jsp">Đăng ký</a>
        </c:if>

        <c:if test="${not empty sessionScope.account}">
            Xin chào, 
            <b>${sessionScope.account.username}</b>
            <a href="profile">Hồ sơ</a>
            <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </c:if>

    </div>
</div>

<div class="content">
    <h2>Chào mừng đến với PRJ301 Shop</h2>
</div>

</body>
</html>
