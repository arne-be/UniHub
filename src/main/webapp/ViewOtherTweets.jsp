<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${tweets.isEmpty() }">
<div class="postcard">
  <p> You have not published any tweets yet... Start sharing now! </p>
</div>
</c:if>

<c:forEach var="t" items="${tweets}">       
 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
   <h4> ${t.uname} </h4><br>
   <hr class="w3-clear">
   <p id="tweetContent" class="tweetContent"> ${t.content} </p>
   <p id="edit" class="edit tweetform" contenteditable="true" hidden="hidden"> ${t.content} </p>
   <br>
   
   
   <button type="button" id="likeTweet" class="likeTweet w3-row w3-button purple-button w3-section"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
   
   <c:if test="${user.getUsertype() == 'Administrator'}">
   	<button type="button" id="editTweet" class="editTweet w3-row w3-button purple-button w3-section"><i class="fa fa-edit"></i> &nbsp;Edit</button> 
   	<button type="button" id="saveEdit" class="saveEdit w3-row w3-button purple-button w3-section" hidden="hidden"><i id="saveEdit" class="saveEdit fa fa-edit"></i> &nbsp;Update</button>
   	<button type="button" id="deleteTweet" class="delTweet w3-row w3-button purple-button w3-section"><i class="fa fa-trash"></i> &nbsp;Delete</button>
   </c:if> 
 </div>
</c:forEach>
