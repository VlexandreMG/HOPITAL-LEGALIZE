<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
    <form action="login.jsp" method="post">
        <p>User : <input type="text" name="user" placeholder="Utilisateur"></p>
        <p>Password : <input type="password" name="pwd" placeholder="Mot de passe"></p>
        <button type="submit">Connexion</button>
    </form>
    
    <%
    String error = request.getParameter("error");
    if (error != null) {
    %>
        <div style="color: red; margin-top: 10px;">
            Erreur: <%= error %>
        </div>
    <%
    }
    %>
</body>
</html>