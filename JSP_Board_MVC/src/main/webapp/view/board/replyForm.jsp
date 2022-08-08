<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h2>게시판 답변 입니다.</h2>
<form action="BoardReplyReg" method="post" >
  <input type="hidden" name="id" value="${dto.id}">
  <input type="hidden" name="gid" value="${dto.gid}">
  <input type="hidden" name="seq" value="${dto.seq}">
  <input type="hidden" name="level" value="${dto.level}">
  <table border="">
    <tr>
      <td width="100px">제목</td>
      <td  width="500px"><input type="text" name="title" value="[Re]${dto.title}"/></td>
    </tr>
    <tr>
      <td>작성자</td>
      <td><input type="text"  name="pname" value="${dto.pname}"/></td>
    </tr>
    <tr>
      <td>암호</td>
      <td><input type="text"  name="pw" /></td>
    </tr>
    <tr>
      <td>내용</td>
      <td><textarea cols="50" rows="5"  name="content" >[Re]${dto.content}</textarea></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="답변" />
        <a href="BoardDetail?id=${dto.id}">뒤로</a>
      </td>
    </tr>
  </table>
</form>