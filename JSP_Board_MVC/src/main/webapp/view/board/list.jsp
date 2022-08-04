<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table border="">
    <tr>
        <td colspan="5" align="center">
            작성자:<input type="text" name="pname"/>
            <input type="button" value="검색">
        </td>
    </tr>
    <tr>
        <td>번호</td>
        <td>제목</td>
        <td>작성자</td>
        <td>작성일</td>
        <td>조회수</td>
    </tr>
    <c:forEach items="${mainData}" var="dto" varStatus="no">
        <tr>
            <td>${no.index+1}</td>
            <td><a href="BoardDetail?id=${dto.id}">${dto.title}</a></td>
            <td>${dto.pname}</td>
            <td><fmt:formatDate value="${dto.reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${dto.cnt}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5" align="center"></td>
    </tr>
    <tr>
        <td colspan="5" align="right">
            <a href="BoardInsertForm">글쓰기</a>
        </td>
    </tr>

</table>