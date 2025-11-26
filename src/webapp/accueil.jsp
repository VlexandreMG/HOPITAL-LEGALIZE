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
    <h2>Bienvenue sur la page d'accueil , <%= medecin.getNom() %> <%= medecin.getPrenom() %></h2>
    <h3>Que souhaitez-vous faire ?</h3> 
    <a href="consultation.jsp">Voir les consultations</a>
    <a href="inventaire.jsp">Faire l'inventaire</a>
</body>
</html>