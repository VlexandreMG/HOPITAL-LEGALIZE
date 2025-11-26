<%@ page import="model.*" %>
<%@ page import="dao.MedmedecinDAO" %>
<%@ page import="java.sql.SQLException" %>

<% 
Users utilisateur = (Users) session.getAttribute("utilisateur");
Medmedecin medecin = (Medmedecin) MedmedecinDAO.getByIdUser(utilisateur.getIdUser());
%>

<!DOCTYPE html>
<html>
<head>
    <title>accueil</title>
</head>
<body>
    <h1>Bienvenue sur la page d'accueil , <%= medecin.getNom() %> <%= medecin.getPrenom() %></h1> 
    <!-- j'aimerais prendre le getuser ici. -->
    <a href="consultation.jsp">Voir les consultations</a>
    <a href="inventaire.jsp">Faire l'inventaire</a>
</body>
</html>