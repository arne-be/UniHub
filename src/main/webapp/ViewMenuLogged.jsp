<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<style>
    /* CSS for the horizontal top-aligned menu */
    .sidebar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: purple; /* Added background color */
        padding: 10px;
        width: 100%;
        margin-left: auto; /* Added margin-left: auto */
    }
    
    .sidebar .w3-bar {
        display: flex;
        align-items: center;
    }
    
    .sidebar .w3-bar-item {
        margin: 0 10px;
    }
    
    .unihub-image {
        display: block;
        width: 125px; 
        height: auto; 
    }
    
    .menu:hover {
        background: linear-gradient(to bottom, #8e2de2, #4a00e0);
        color: #fff !important;
    }
    
    .menu {
        color: white; 
    }
    
    .logout-icon {
        margin-left: auto; /* Added margin-left: auto */
    }
</style>

<div class="sidebar">
    <img class="unihub-image" src="imgs/UniHub.png" alt="UniHub">
    <div class="w3-bar">
        <a class="menu w3-bar-item w3-button w3-hide-small" id="GetOwnTimeline" href="#" style="font-weight: bold; font-size: 20px;">Profile</a>
        <a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedUsers" href="#" style="font-weight: bold; font-size: 20px;">Following</a>
        <a class="menu w3-bar-item w3-button w3-hide-small" id="GetTimeline" href="#" style="font-weight: bold; font-size: 20px;">Timeline</a>
        <a class="menu w3-bar-item w3-button w3-hide-small" id="LogoutController" href="#" style="display: flex; align-items: center; font-weight: bold; font-size: 20px;"><i class="fa fa-sign-out"></i></a>
    </div>
    <a href="#" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
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
