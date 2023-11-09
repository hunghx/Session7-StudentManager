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
 <jsp:include page="../component/head.jsp"></jsp:include>
</head>
<body>

<h1>Thêm mới sinh viên</h1>

<form action="/StudentServlet" method="post">
  <div class="mb-3">
    <label for="formGroupExampleInput" class="form-label">Tên sinh viên</label>
    <input type="text" class="form-control" id="formGroupExampleInput" placeholder="tên sinh viên" name="name">
  </div>
  <div class="mb-3">
    <label for="formGroupExampleInput2" class="form-label">Số điện thoại</label>
    <input type="text" class="form-control" id="formGroupExampleInput2" name="phone">
  </div>
  <div class="mb-3">
    <label for="formGroupExampleInput3" class="form-label">Địa chỉ</label>
    <input type="text" class="form-control" id="formGroupExampleInput3" name="address">
  </div>
  <div class="mb-3">
    <label for="formGroupExampleInput5" class="form-label">Tuổi</label>
    <input type="number" min="18" max="100" class="form-control" id="formGroupExampleInput5" name="age">
  </div>
  <div class="mb-3">
    <label for="formGroupExampleInput4" class="form-label">Giới tính</label>
    <select name="sex" id="formGroupExampleInput4">
      <option value="true">Nam</option>
      <option value="false">Nữ</option>
    </select>
  </div>
  <input type="submit" value="ADD" name="action">
<%--  <button type="submit" name="action">Add</button>--%>
</form>
<jsp:include page="../component/foot.jsp"></jsp:include>
</body>
</html>
