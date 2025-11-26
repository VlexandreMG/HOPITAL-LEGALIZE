<%@ page import="model.*" %>

<% 
Users utilisateur = (Users) session.getAttribute("utilisateur");
%>

<!DOCTYPE html>
<html>
<head>
    <title>accueil</title>
</head>
<body>
    <h1>Bienvenue sur la page d'accueil , <%= utilisateur.getNom() %></h1> 
    <!-- j'aimerais prendre le getuser ici. -->
    <a href="consultation.jsp">Voir les consultations</a>
    <a href="inventaire.jsp">Faire l'inventaire</a>
</body>
</html>