<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h4>Witaj użytkowniku o loginie: ${email}</h4>
<h4>Aktywuj swoje konto wchodząc na podany poniżej link</h4>

--------------------------------------------------------------------------------------------------------<br>


<#--<a href="http://localhost:8080/activation/${id}/${token}" >Aktywacja Konta</a>-->
<a href="https://charity-web-service.herokuapp.com/activation/${id}/${token}" >Aktywacja Konta</a>


--------------------------------------------------------------------------------------------------------<br>


</body>
</html>