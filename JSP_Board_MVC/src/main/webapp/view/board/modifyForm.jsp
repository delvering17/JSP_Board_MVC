<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
  <c:if test="${msg!=null }">
  alert("${msg}")
  </c:if>


  function fileDelete(){
    if(confirm("파일을 삭제하시겠습니까?\n삭제한 파일은 복원할수 없습니다.")){
      var frm = document.frm
      frm.action="FileDelete"
      frm.submit()
    }
  }
</script>
<h2>게시판 수정 입니다.</h2>
<form action="BoardModifyReg" method="post" enctype="multipart/form-data" name="frm">
  <input type="hidden" name="id" value="${dto.id }"/>
  <table border="">
    <tr>
      <td width="100px">제목</td>
      <td  width="500px"><input type="text" name="title" value="${dto.title }"/></td>
    </tr>
    <tr>
      <td>작성자</td>
      <td><input type="text"  name="pname" value="${dto.pname }"/></td>
    </tr>
    <tr>
      <td>암호</td>
      <td><input type="text"  name="pw" /></td>
    </tr>
    <tr>
      <td>파일</td>
      <td>
        <c:choose>
          <c:when test="${dto.upfile!=null }">
            <input type="button"  value="파일삭제" onclick="fileDelete()"/>
            <input type="hidden"  name="upfile" value="${dto.upfile}"/>
          </c:when>
          <c:otherwise>
            <input type="file"  name="upfile"/>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
    <tr>
      <td>내용</td>
      <td><textarea cols="50" rows="5"  name="content" >${dto.content }</textarea></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="수정" />
        <a href="BoardModifyForm?id=${dto.id }">초기화</a>
        <a href="BoardDetail?id=${dto.id }">뒤로</a>
      </td>
    </tr>
  </table>
</form>
