<%--
  Created by IntelliJ IDEA.
  User: song-chanwook
  Date: 2022/08/04
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>template</title>
</head>
<body>
    <table border="">
        <tr>
            <td>
                <jsp:include page="inc/top.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                <jsp:include page="${mainUrl}"/>
            </td>
        </tr>
        <tr>
            <td>
                <jsp:include page="inc/bottom.jsp"/>
            </td>
        </tr>


    </table>

</body>
</html>
