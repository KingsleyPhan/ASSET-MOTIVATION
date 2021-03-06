<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>ĐIỀU ĐỘNG TÀI SẢN</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
  <link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" rel="stylesheet" />
  <link type="text/css" href="resources/css/main.css" rel="stylesheet" />
<style>
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

.button {
  background-color: #0963b1;
    color: white;
    padding: 9px 8px 4px 8px;
    /* margin: 8px 0; */
    border: none;
    cursor: pointer;
    width: 120px;
    font-weight: 700;
    border-radius: 0px;
    font-size: 16px;
    text-align: center;
}

.button:hover {
  opacity: 0.8;
    color: white;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }

}
  form {
      margin: auto;
      position: inherit;
      top: 25%;
      left: 38%;
  }
</style>
</head>
<body>
<div style="background-color: #0680e8; padding: 1px; color: #FFFFFF; width:100%; padding-left: 20px;">
	<h1 style="font-size: 26px;">HỆ THỐNG QUẢN LÝ ĐIỀU ĐỘNG TÀI SẢN</h1>
</div>
<div style="background-color: #0963b1; padding: 1px; color: #FFFFFF; width:100%; padding-left: 20px; height: 35px">
</div>
<div class="container" style="width:100%; padding: 5px;">

    <form action="DangNhap" method="post" style="width:450px; padding: 10px; position: absolute;">
  <div class="imgcontainer">
    <h3>ĐĂNG NHẬP </h3>
  </div>

  <div>
    <label for="uname"><b>TÊN ĐĂNG NHẬP </b></label>
    <input type="text" placeholder="Tên đăng nhập" name="usn" required>

    <label for="psw"><b>Mật khẩu</b></label>
    <input type="password" placeholder="Mật khẩu người dùng" name="pwd" required>
    <span class="error">${error}</span>
    <center>
    <button type="submit" class="btn button" name="login">ĐĂNG NHẬP</button>
    </center>
  </div>
</form>
</div>
<div style="background-color: #0680e8; padding: 1px; color: #FFFFFF; width:100%; padding-right: 20px; position: absolute; bottom: 0px">
	<h1 style="font-size: 20px; text-align: right; margin-right: 10px;">Version 1.0</h1>
</div>
</body>
</html>
