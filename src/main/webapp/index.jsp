<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%response.sendRedirect("/StudentServlet?action=GETALL");%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<h1><%= "Hello World!" %>
</h1>


<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="time">Get Time</a>

<%--ấn vào nút lấy ngày giờ thì hiển thị ra ngày giờ hệ thống--%>
<form action="/calculator" method="post">
    <div class="row">
        <div class="col-2">
            <input type="text" value="${a}" class="form-control" placeholder="A" name="a" >
        </div>
        <div class="col-1">
            <span>+</span>
        </div>
        <div class="col-2">
            <input type="text" value="${b}" class="form-control" placeholder="B"  name="b">
        </div>
        <div class="col-1">
            <span>=</span>
        </div>
        <div class="col-2">
            <input type="text" value="${total}" class="form-control" readonly placeholder="Kết quả" aria-label="Last name">
        </div>
    </div>
    <button type="submit">Tính</button>
</form>


<a href="/StudentServlet?action=GETALL">Danh sách sinh viên</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>