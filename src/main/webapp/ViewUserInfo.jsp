<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile Page</title>
  <style>
    .purple-button {
      background-color: purple;
      color: white;
    }
  </style>
</head>
<body>
  <div id ="${user.id}" class="w3-container w3-card w3-round w3-white w3-section w3-center">
    <h4>My Profile</h4>
    <p><img src="imgs/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
    <hr>
     <p class="showProfile w3-left-align profileform"> <i class="fa fa-user fa-fw w3-margin-right"></i> ${user.username} </p>
    <p class="showProfile w3-left-align profileform"> <i class="fa fa-id-card fa-fw w3-margin-right"></i> ${user.name}</p>
    <p class="showProfile w3-left-align profileform"> <i class="fa fa-id-badge fa-fw w3-margin-right"></i> ${user.mail} </p>
    <p class="showProfile w3-left-align profileform"> <i class="fa fa-birthday-cake fa-fw w3-margin-right"></i> ${user.datebirth} </p>
    <p class="showProfile w3-left-align profileform"> ${user.about}</p>
    
    <!--AHORA AÑADIMOS PARA EDITAR-->
    <p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-user fa-fw w3-margin-right" ></i> <input type="text" id="username" class="editProfile w3-left-align profileform" hidden = "hidden" value="${user.username}" required></p>
    <p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-id-card fa-fw w3-margin-right" ></i> <input type="text" id="name" class="editProfile w3-left-align profileform" hidden = "hidden" value="${user.name}" required> </p>
    <p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-id-badge fa-fw w3-margin-right" ></i> <input type="email" id="email" class="editProfile w3-left-align profileform" hidden = "hidden" value="${user.mail}" required></p>
    <p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-birthday-cake fa-fw w3-margin-right" ></i> <input type="date" id="dob" class="editProfile w3-left-align profileform" hidden = "hidden" value="${user.datebirth}" required></p>
    <p hidden="hidden" class="editProfile w3-left-align profileform"><i class="fa fa-id-badge fa-fw w3-margin-right" ></i> <input id="about" class="editProfile w3-left-align profileform" hidden = "hidden" value="${user.about}" required></p>
    

    <!-- LOS BOTONES SIGUEN IGUAL-->
    <button type="button" id ="editUser" class="w3-row w3-button purple-button w3-section"><i class="fa fa-edit"></i> &nbsp;Edit</button>   
  	<button type="submit" id="saveProfile" class="w3-row w3-button purple-button w3-section" hidden="hidden" > &nbsp;Save changes</button>
  	<button type="button" id="deleteOwnProfile"  class="w3-row w3-button purple-button w3-section" > &nbsp;Delete Profile</button> 

  </div>
  <br>

  <!-- Mostraremos el tipo de usuario elegido anteriormente -->
  <p id="selectedOption"></p>

  <script>
    // Obtén el valor de la opción seleccionada de la URL
    var urlParams = new URLSearchParams(window.location.search);
    var selectedOption = urlParams.get('option');
    
    var userId = ${user.id};

    // Actualiza el contenido del elemento con el valor seleccionado
    document.getElementById("selectedOption").textContent = selectedOption;
  </script>
</body>
</html>
