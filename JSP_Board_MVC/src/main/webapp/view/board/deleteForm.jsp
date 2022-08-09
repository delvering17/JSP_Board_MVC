<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h2>게시판 삭제 입니다.</h2>
<form action="BoardDeleteReg">
    <input type="hidden" name="id" value="${param.id }"/>
    <input type="hidden" name="nowPage" value="${nowPage }"/>
    <table border="" width="100%">
        <tr>
            <td>암호</td>
            <td><input type="text" name="pw"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="삭제" />
                <a href="BoardDetail?id=${param.id }&nowPage=${nowPage}">뒤로</a>
            </td>
        </tr>
    </table>
</form>