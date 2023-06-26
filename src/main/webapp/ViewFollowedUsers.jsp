<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${users.isEmpty() }">
<div class="postcard">
  <p> You don't follow any users... </p>
</div>
</c:if>
<c:forEach var="u" items="${users}">       
 <div id="${u.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <h4> ${u.username} </h4><br>
   <hr class="w3-clear">
   <button type="button" class="userInfo w3-button w3-theme w3-margin-bottom"><i class="fa fa-info-circle"></i> &nbsp;Info</button>
   <button type="button" class="unfollowUser w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Unfollow</button> 
   
   <c:if test = "${user.getUsertype() == 'Administrator'}">  
   <button type="button" class="deleteProfile w3-margin-bottom">Delete</button> 
   </c:if>
 </div>
</c:forEach>