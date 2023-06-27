<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#lcolumn').load('GetNotFollowedUsers');
	$('#rcolumn').load('GetOtherUserInfo');
	$('#iterator').load('GetOtherUserTweets');
 });
</script>

 
<div id="iterator">
</div>

 