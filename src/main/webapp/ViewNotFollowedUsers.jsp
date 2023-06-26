<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style> 
   .light-blue-button {
        background-color: #87CEEB; 
}

html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

body {
  position: relative;
  overflow-x: hidden;
  background: linear-gradient(135deg, #ffc0cb, #dda0dd, #b0e0e6);
}

</style>


<c:forEach var="u" items="${users}">       
<div id="${u.id}" class="w3-container w3-card w3-round w3-white w3-center w3-section">
	<p>Friend Suggestion</p>
    <img src="imgs/avatar6.png" alt="Avatar" style="width:50%"><br>
    <div>${u.username}</div>
    <button type="button" class="userInfo w3-row w3-button light-blue-button w3-section"><i class="fa fa-info-circle"></i> &nbsp;Info</button>
    <button type="button" class="followUser w3-row w3-button light-blue-button w3-section"><i class="fa fa-user-plus"></i> &nbsp;Follow</button> 
</div>
</c:forEach>

