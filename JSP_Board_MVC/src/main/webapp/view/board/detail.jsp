<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table border="">
    <tr>
        <td colspan="2">
            <h2>게시판 상세보기 입니다.</h2>
        </td>
    </tr>
    <tr>
        <td>id</td>
        <td>${dto.id}</td>
    </tr>
    <tr>
        <td>제목</td>
        <td>${dto.title}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${dto.pname}</td>
    </tr>
    <tr>
        <td>작성일</td>
        <td>${dto.reg_dateStr}</td>
    </tr>
    <tr>
        <td>조회수</td>
        <td>${dto.cnt}</td>
    </tr>
    <c:if test="${dto.upfile != null}">
        <tr>
            <td>파일</td>
            <td>
            <c:choose>
                <c:when test="${dto.img}">
                    <img src="<c:url value="/fff/"/>${dto.upfileEn}"/>

                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/nonView/FileDown"/>?upfile=${dto.upfileEn }">${dto.upfile }다운로드</a>
                </c:otherwise>
            </c:choose>
            </td>
        </tr>
    </c:if>
    <tr>
        <td>내용</td>
        <td>${dto.contentBr}</td>
    </tr>
    <tr>
        <td>
            <a href="BoardModify?id=${dto.id}">수정</a>
            <a href="BoardDelete?id=${dto.id}">삭제</a>
            <a href="BoardList">목록으로</a>
        </td>
    </tr>


</table>