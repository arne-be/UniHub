<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
    
    
<style>
    /* CSS for the vertical left-aligned menu */
    .sidebar {
        position: fixed;
        left: 0px; /* Adjust the left position as needed */
        top: 240px; /* Adjust the top position as needed */
        bottom: 50px; /* Adjust the bottom position as needed */
        width: 200px;
        background-color: transparent;
    }
    
    .sidebar .w3-bar {
        display: flex;
        flex-direction: column;
        height: 100%;
        justify-content: flex-start;
        align-items: flex-start;
    }
    
    .sidebar .w3-bar-item {
        width: 100%;
    }
    
    .sidebar .w3-bar-item a {
        display: block;
        width: 100%;
    }
    
    .sidebar .w3-bar-block {
        width: 100%;
    }
    
    .unihub-image {
      display: block;
      width: 125px; /* Adjust the width as needed */
      height: auto; /* Adjust the height as needed */
      margin: 10px auto; /* Adjust the margin as needed */
    }
    
    .menu:hover {
      background: linear-gradient(to bottom, #8e2de2, #4a00e0);
      color: #fff !important;
    }
</style>

<div class="sidebar">
    <div class="w3-bar">
        <img class="unihub-image" src="imgs/UniHub.png" alt="UniHub">
        <a class="menu w3-bar-item w3-button w3-hide-small" id="GetOwnTimeline" href="#" style="font-weight: bold; font-size: 20px;">Profile</a>
        <a href="#" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
        <a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedUsers" href="#" style="font-weight: bold; font-size: 20px;">Following</a>
        <a href="#" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
        <a class="menu w3-bar-item w3-button w3-hide-small" id="LogoutController" href="#" style="font-weight: bold; font-size: 20px;">Log out</a>
        <a href="#" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
    </div>
    <div id="stack" class="w3-bar-block w3-hide w3-hide-large w3-hide-medium">
        <a class="menu w3-bar-item w3-button" id="LoginController" href="#">Login</a>
    </div>
</div>

<script>
    function stack() {
        var stackElement = document.getElementById("stack");
        stackElement.classList.toggle("w3-hide");
    }
</script>