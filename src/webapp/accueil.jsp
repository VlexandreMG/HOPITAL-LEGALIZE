<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%
List<MedConsultation> listeConsultations2 = new ArrayList<>(); 
Users utilisateur = (Users) session.getAttribute("utilisateur");
Medmedecin medecin = MedmedecinDAO.getByIdUser(utilisateur.getIdUser());
session.setAttribute("medecin", medecin);
listeConsultations2 = MedConsultationDAO.getByIdMedecin(medecin.getMatricule());
%>

<!DOCTYPE html>
<html>
<head>
    <title>accueil</title>
</head>
<body>
    <h2>Bienvenue sur la page d'accueil , <%= medecin.getNom() %> <%= medecin.getPrenom() %></h2>
    <h3>Que souhaitez-vous faire ?</h3> 
    <a href="CreateConsultation.jsp">Ajouter une consultation</a>
    <a href="inventaire.jsp">Faire l'inventaire</a>

    <hr width="100%" size="2" color="black">

   <table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Heure Arrivée</th>
        <th>Heure Départ</th>
        <th>Patient</th>
        <th>Action</th>
    </tr>
    <% 
    for(MedConsultation consultation2 : listeConsultations2) { 
    %>
    <tr>
        <form action="ordonnance.jsp" method="post">
            <td><%= consultation2.getDaty() %></td>
            <td><%= consultation2.getDescription() %></td>
            <td><%= consultation2.getHeureArrivee() %></td>
            <td><%= consultation2.getHeureDepart() %></td>
            <td><%= consultation2.getPatient() %></td>
            <td>
                <input type="hidden" name="idConsultation" value="<%= consultation2.getId() %>">
                <input type="submit" value="Voir">
            </td>
        </form>
    </tr>
    <% } %>
</table>
</body>
</html>