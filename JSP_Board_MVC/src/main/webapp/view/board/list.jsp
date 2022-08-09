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
    <c:forEach items="${mainData }" var="dto" varStatus="no">
        <tr>
            <td>${first + no.index+1 }</td>
            <td>
                <c:forEach begin="1" end="${dto.level}">
                    <c:if test="${dto.level != 0}">
                        ⎿
                    </c:if>
                </c:forEach>
                <a href="BoardDetail?id=${dto.id }&nowPage=${nowPage}">${dto.title }</a>
            </td>
            <td>${dto.pname }</td>
            <td>
                <fmt:formatDate value="${dto.reg_date }" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td>${dto.cnt }</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5" align="center">
            <c:if test="${firstPage > 1}">
                <a href="?nowPage=${firstPage - 1}">이전</a>
            </c:if>
            <c:forEach begin="${firstPage}" end="${endPage}" var="i">
                <c:choose>
                    <c:when test="${i == nowPage}">
                        [${i}]
                    </c:when>
                    <c:otherwise>
                        <a href="?nowPage=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${endPage < totalPage}">
                <a href="?nowPage=${endPage + 1}">다음</a>
            </c:if>

        </td>
    </tr>
    <tr>
        <td colspan="5" align="right">
            <a href="BoardInsertForm?nowPage=${nowPage}">글쓰기</a>
        </td>
    </tr>
</table>