<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Logout</title>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
  <style>
    html, body {
      height: 100%;
    }
    
    body {
      background: linear-gradient(135deg, #ffc0cb, #dda0dd, #b0e0e6);
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
      min-height: 100%;
      margin: 0;
      padding: 0;
    }
    
    .logout-container {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
      font-family: 'Montserrat', sans-serif;
      font-size: 24px;
      text-align: center;
    }
  </style>
</head>

<body>
  <div class="logout-container">
    <p>Log out done</p>
  </div>

  <script type="text/javascript">
    $(document).ready(function() {
      $('#navigation').load('MenuController');
      $('#lcolumn').html("<p></p>");
      $('#rcolumn').html("<p></p>");
    });
  </script>
</body>

</html>
