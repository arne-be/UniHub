<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- P3 CODE: lines 7-13; 24-37; 42-44 -->

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  <link rel="stylesheet" type="text/css" href="css/ViewLoginForm.css">
</head>

<!-- AQUÍ AÑADIMOS TODAS LAS VARIABLES QUE PUEDEN CONTENER ERRORES -->
<c:if test = "${login.error['username']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Login error! </h3>
  <p> Username doesn't exist in our database.</p>
</div>
</c:if>

<c:if test = "${login.error['pwd']}">
<div class="w3-panel w3-theme-l4 w3-display-container">
  <span onclick="this.parentElement.style.display='none'"
  class="w3-button w3-large w3-display-topright">&times;</span>
  <h3> Login error! </h3>
  <p> Incorrect password!Try again.</p>
</div>
</c:if>


  <div class="required-form" id="required-form">
    <h1>LOG IN</h1>
    <form action="LoginController" method="POST">
      <p>
        <label for="user" class="w3-text-grey"> Username </label><br>
        <input class="w3-input w3-border w3-light-grey" type="text" name="username" placeholder="Username" required autocomplete="username" value="${login.username}" required autocomplete="username"><br>
        
        <label for="pwd" class="w3-text-grey"> Password: </label><br>
        <input class="w3-input w3-border w3-light-grey" type="password" id="pwd" name="pwd" placeholder="Password" value="${login.pwd}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&_-])[A-Za-z\d@$!%*?&_-]{6,12}$" autocomplete="current-password"><br><br>
        
        
        <!--he puesto la checkbox del remember me-->
        <input type="checkbox" name="rememberMe" id="myCheck" value="${login.rememberMe}">
        <label>Remember me</label>

        <!-- He comentado la siguiente línea que era original del P4 !-->
        <!-- <input class="w3-btn w3-theme" type="submit" name="sumbit" value="Submit"> !-->

        <div style="display: flex; justify-content: center;">
          <button type="submit" style="width: 200px; height: 50px; background: linear-gradient(135deg, pink, blue); color: white; font-size: 18px; border: none; border-radius: 5px; cursor: pointer; margin-top: 20px; margin-left: auto; margin-right: auto;">Submit</button>
        </div>
    </form>
  </div>
</body>