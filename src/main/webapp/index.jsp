<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/ourLogo.png">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Lab 4 template </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!--P3 CODE: lines 11 & 69 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-red.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">
$(document).ready(function(){
	
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".menu",function(event) {
		$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	$(document).on("submit","form", function(event) {
		$('#content').load($(this).attr('action'),$(this).serialize());
	    event.preventDefault();
	});
	$(document).on("click","#userInfo",function(event) {
		var u_id = $(this).parent();
		$('#rcolumn').load( "GetOtherUserInfo", {id: u_id.attr("id")});
		$("#content").load("GetOtherUserTweets", {id: u_id.attr("id")});
		event.preventDefault();
		
	});
	$(document).on("click","#userInfoFromTweet",function(event) {
		var u_id = $(this).parent();
		$('#rcolumn').load( "GetOtherUserInfo", {id: u_id.attr("uid")});
		$("#content").load("GetOtherUserTweets", {id: u_id.attr("uid")});
		event.preventDefault();
		
	});
	/* Add tweet */
	$(document).on("click","#addTweet",function(event){
		$.post( "AddTweet", { content: $("#tweetContent").text()}, function(event) {
			$("#content").load("GetOwnTimeline");		
		});
		event.preventDefault();
	});
	/* Delete tweet */
	$(document).on("click",".delTweet",function(event){
		var tweet = $(this).parent();
		$.post( "DelTweet", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	/* Edit Tweet */
	$(document).on("click","#editTweet",function(event){
		var tweet = $(this).parent();
		tweet.find(".edit").show();
		tweet.find(".tweetContent").hide();
		$(this).hide();
		tweet.find("#editTweet").hide();
		tweet.find("#saveEdit").show();
		event.preventDefault();
	});
	$(document).on("click","#saveEdit",function(event){
		var tweet = $(this).parent();
		tweet.find(".edit").hide();
		tweet.find(".tweetContent").text(tweet.find(".edit").text())
		tweet.find(".tweetContent").show();
		$(this).hide();
		tweet.find("#editTweet").show();
		$.post( "EditTweet", { id: tweet.attr("id"), content: tweet.find(".edit").text()});
		event.preventDefault();
	});
	
	/* Follow user */
	$(document).on("click",".followUser",function(event){
		var user = $(this).parent();
		$.post( "FollowUser", { id: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* UnFollow user */
	$(document).on("click",".unfollowUser",function(event) {
		var user = $(this).parent();
		$.post( "UnFollowUser", { id: $(this).parent().attr("id") }, function(event) {
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* Edit Profile */
	$(document).on("click","#editUser",function(event){
			$(".editProfile").show();
			$(".showProfile").hide();
			$(this).hide();
			$("#saveProfile").show();
			event.preventDefault();
		});	
		$(document).on("click","#saveProfile",function(event){
			$('#rcolumn').load( "SaveProfile", { id: $(this).parent().attr("id"), username: $("#username").val(), name: $("#name").val(), mail: $("#email").val(),
				 about: $("#about").val(), datebirth: $("#dob").val()} );
			user.getError("user")
			$(".editProfile").hide();
			$(".showProfile").show();
			$("#saveProfile").hide();
			$(this).hide();			
			event.preventDefault();
		});
		
		/* Delete Own Profile */
		$(document).on("click","#deleteOwnProfile",function(event){
			var user = $(this).parent();
			$.post("DelUser", { id: $(this).parent().attr("id")}, function(event){
				$("#content").load("GoodbyeController");
			});
			event.preventDefault();
		});
		/*Delete other profile*/
		$(document).on("click","#deleteProfile",function(event){
			var user = $(this).parent();
			$.post("DelUser", { id: $(this).parent().attr("id")}, function(event){
				$("#content").load("GetOwnTimeline");
			});
			event.preventDefault();
		});
		
});
</script>
</head>
<body>

 	<!-- Begin Navigation -->
	<div class="w3-bar" id="navigation">
		<!--La línea 71 es la que venía en el código, la he comentado para seguir la P3-->
 	<!-- <div class="w3-theme" id="navigation"> !-->
    	<jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
 
 	<!-- Begin Content -->
	<div class="w3-row-padding">
 	<!-- Left Column -->
	<div class="w3-container w3-col m3 w3-hide-small">
		<div id="rcolumn">
			<p></p>
		</div>
	</div>
	<!-- Middle Column -->	
	<div class="e3-container w3-col m6">
		<div id="content">
			<jsp:include page="${content}" />
		</div>
	</div>
	<!-- Right Column -->
	<div class="w3-container w3-col m3 w3-hide-small">
		<div id="lcolumn">
			<p></p>
		</div>
	</div>
	</div>
	<!-- End Content -->
	
	<script>
		function stack() {
  			var x = document.getElementById("stack");
  			if (x.className.indexOf("w3-show") == -1) {
    			x.className += " w3-show";
  			} else { 
    		x.className = x.className.replace(" w3-show", "");
  			}
		}
	</script>

  </body>
</html>