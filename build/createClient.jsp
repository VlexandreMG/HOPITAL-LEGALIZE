<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Création de client </title>
</head>
<body>
    <h3>Ajouter votre nouveau client : </h3>
    <form action="addClient" method="post">
        <p>Nom : <input type="text" name="nom"></p>
        <p>Numéro de telephone : <input type="text" name="telephone"></p>
        <input type="submit" value="Ajouter le client">
    </form>
</body>
</html>
