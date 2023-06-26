<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<style>
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

.container {
  display: flex;
}

</style>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#lcolumn').load('GetNotFollowedUsers');
	$('#rcolumn').load('GetUserInfo');
	$('#iterator').load('GetUserTweets');
 });
</script>

<div class="w3-container w3-card w3-round w3-white w3-section">
	<h6 class="w3-opacity"> ${user.username}, what are you thinking? </h6>
	<p id="tweetContent" contenteditable="true" class="w3-border w3-padding"> </p>
	<button id="addTweet" type="button" class="w3-row w3-button purple-button w3-section"><i class="fa fa-pencil"></i> &nbsp;Post</button> 
</div>
 
<div id="iterator">
</div>