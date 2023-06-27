<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${tweets.isEmpty() }">
<div class="postcard">
  <p> There are not published tweets yet. </p>
</div>
</c:if>

<c:forEach var="t" items="${tweets}">       
 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
   <h4> ${t.uname} </h4><br>
   <hr class="w3-clear">
   <p id="tweetContent" class="tweetContent"> ${t.content} </p>

   <br>   
   <button type="button" class="userInfo w3-row w3-button" style="background-color: purple; color: white;"><i class="fa fa-info-circle"></i> &nbsp;Info</button>
   
</div>

</c:forEach>
