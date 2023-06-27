<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id ="${otherUser.id}" class="w3-container w3-card w3-round w3-white w3-section w3-center">
    <h4>Profile</h4>
    <p><img src="imgs/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
    <hr>
    <p class="showProfile w3-left-align profileform"> <i class="fa fa-user fa-fw w3-margin-right"></i> ${otherUser.username} </p>
    <p class="showProfile w3-left-align profileform"> <i class="fa fa-id-card fa-fw w3-margin-right"></i> ${otherUser.name}</p>
    <p class="showProfile w3-left-align profileform"> <i class="fa fa-id-badge fa-fw w3-margin-right"></i> ${otherUser.mail} </p>
    <p class="showProfile w3-left-align profileform"> <i class="fa fa-birthday-cake fa-fw w3-margin-right"></i> ${otherUser.datebirth} </p>
    <p class="showProfile w3-left-align profileform"> ${otherUser.about}</p>
    
    <button id="followUser" type="button" class="followUser w3-row w3-button purple-button w3-section"><i class="fa fa-user-plus" ></i> &nbsp;Follow</button>
	<button id="unfollowUser" type="button" class="unfollowUser w3-row w3-button purple-button w3-section"><i class="fa fa-user-plus" ></i> &nbsp;Unfollow</button>
    
    <c:if test= "${user.usertype == 'Administrator'}">
    	<!--AHORA AÑADIMOS PARA EDITAR-->
    	<p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-user fa-fw w3-margin-right" ></i> <input type="text" id="username" class="editProfile w3-left-align profileform" hidden = "hidden" value="${otherUser.username}" required></p>
    	<p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-id-card fa-fw w3-margin-right" ></i> <input type="text" id="name" class="editProfile w3-left-align profileform" hidden = "hidden" value="${otherUser.name}" required> </p>
    	<p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-id-badge fa-fw w3-margin-right" ></i> <input type="email" id="email" class="editProfile w3-left-align profileform" hidden = "hidden" value="${otherUser.mail}" required></p>
    	<p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-birthday-cake fa-fw w3-margin-right" ></i> <input type="date" id="dob" class="editProfile w3-left-align profileform" hidden = "hidden" value="${otherUser.datebirth}" required></p>
    	<p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-id-badge fa-fw w3-margin-right" ></i> <input id="about" class="editProfile w3-left-align profileform" hidden = "hidden" value="${otherUser.about}" required></p>
    

    	<!-- LOS BOTONES SIGUEN IGUAL-->
    	<button type="button" id ="editUser" class="w3-row w3-button purple-button w3-section"><i class="fa fa-edit"></i> &nbsp;Edit</button>   
  		<button type="submit" id="saveProfile" class="w3-row w3-button purple-button w3-section" hidden="hidden" > &nbsp;Save changes</button>
  		<button type="button" id="deleteProfile"  class="w3-row w3-button purple-button w3-section" > &nbsp;Delete Profile</button> 
  </c:if>
</div>