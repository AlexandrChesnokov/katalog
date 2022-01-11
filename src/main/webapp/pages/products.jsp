<%--
  Created by IntelliJ IDEA.
  User: apspr
  Date: 05.12.2021
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
    <title>products</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/websocketApp.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sockjs-0.3.4.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/stomp.js"></script>
</head>

<body>
<table border="1" cellpadding="2" width="30%" align="left">
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Action</th>
    </tr>

    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td> <button value="add to cart" type="button" onclick="sendMessage(${product.id}, 1)">Добавить в корзину</button></td>

        </tr>
    </c:forEach>

    <input type="button" onclick="history.back();" value="Back"/>
</table>

<table id="cart" align="right" border="1"></table>

<br />


<script>
    document.addEventListener('DOMContentLoaded', e => {
connect("${pageContext.request.contextPath}");
    })
</script>
</body>
</html>

