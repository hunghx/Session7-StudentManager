<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 11/8/2023
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>

<h1>Danh sách sinh viên</h1>
<a href="/StudentServlet?action=ADD">Thêm mới sinh viên</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Phone</th>
        <th scope="col">Address</th>
        <th scope="col">Sex</th>
        <th scope="col" colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="c">
        <tr>
            <th>${c.getId()}</th>
            <td>${c.getName()}</td>
            <td>${c.getPhone()}</td>
            <td>${c.getAddress()}</td>
            <td>${c.isSex()?"Nam":"Nữ"}</td>
            <td><a href="/StudentServlet?action=EDIT&id=${c.id}">Edit</a></td>
            <td><a onclick="return confirm('Bạn có chắc chắn muốn xóa không')"
                   href="/StudentServlet?action=DELETE&id=${c.id}">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
