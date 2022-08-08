
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>게시판 삭제 입니다.</h2>
<form action="BoardDeleteReg">
    <input type="hidden" name="id" value="${param.id}">
    <table border="">
        <tr>
            <td>암호</td>
            <td><input type="text" name="pw"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="삭제">
                <a href="BoardDetail?id=${param.id}">뒤로</a>
            </td>
        </tr>

    </table>
</form>