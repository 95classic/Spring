<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<meta charset="UTF-8">
<title>${vo.boardTitle }</title>
</head>
<body>
   <h2>글 보기</h2>
   <div>
      <p>글 번호 : ${vo.boardId }</p>
   </div>
   <div>
      <p>제목 :</p>
      <p>${vo.boardTitle }</p>
   </div>
   <div>
      <p>작성자 : ${vo.memberId}</p>
      				<fmt:formatDate value="${vo.boardDateCreated }"
					pattern="yyyy-MM-dd HH:mm:ss" var="boardDateCreated"/>
      <p>작성일 :${boardDateCreated }</p>
   </div>
   <div>
      <textarea rows="20" cols="120" readonly>${vo.boardContent }</textarea>
   </div>
   <a href="list?page=${page }"><input type="button" value="글 목록"></a>
   <a href="update?boardId=${vo.boardId }&page=${page}"> 
   <input type="button" value="글 수정">
   </a>
   <form action="delete" method="POST">
      <input type="hidden" name="boardId" value="${vo.boardId }"> <input
         type="submit" value="글 삭제">
   </form>
   
 

</body>
</html>