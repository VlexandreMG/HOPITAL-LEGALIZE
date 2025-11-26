<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
    <form action="login.jsp" method="post">
        <input type="text" name="user" placeholder="Utilisateur">
        <input type="password" name="pwd" placeholder="Mot de passe">
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