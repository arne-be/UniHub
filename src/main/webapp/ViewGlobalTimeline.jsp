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
	$('#iterator').load('GetAllTweets');
	$('#rcolumn').load('GetUserInfo');

 });
</script>

<br>
 
<div id="iterator">
</div>