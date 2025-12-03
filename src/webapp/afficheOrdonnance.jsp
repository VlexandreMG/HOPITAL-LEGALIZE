<%@ page import="java.util.Vector" %>
<%@ page import="model.MedOrdonnance" %>
<%@ page import="dao.MedOrdonnanceDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

<%
    // Récupérer toutes les ordonnances depuis la base de données
    Vector<MedOrdonnance> ordonnances = new Vector<>();
    try {
        // Utiliser la méthode DAO pour récupérer les ordonnances
        ordonnances = MedOrdonnanceDAO.getAllOrdonnances();
        //System.out.println("Nombre d'ordonnances récupérées : " + ordonnances.size());
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    int totalItems = ordonnances.size();
%>

    <h3>Liste des ordonnances</h3>
    <table border="1">
    <tr>
        <th>N° Ordonnance</th>
        <th>Consultation</th>
        <th>Médecin</th>
        <th>Durée</th>
        <th>Observation</th>
        <th>Actions</th>
    </tr>
    
    <% if (ordonnances.isEmpty()) { %>
        <tr>
            <td colspan="6">
                Aucune ordonnance trouvée
            </td>
        </tr>
    <% } else { %>
        <% for (MedOrdonnance ord : ordonnances) { %>
        <tr>
            <td><%= ord.getId() %></td>
            <td><%= ord.getIdConsultation() %></td>
            <td><%= ord.getIdMedecin() %></td>
            <td><%= ord.getNbJours() %> jours</td>
            <td>
                <% if (ord.getObservation() != null && !ord.getObservation().trim().isEmpty()) { %>
                    <%= ord.getObservation().length() > 50 ? ord.getObservation().substring(0, 50) + "..." : ord.getObservation() %>
                <% } else { %>
                    Aucune
                <% } %>
            </td>
            <td>
                <a href="ordonnanceView.jsp?id=<%= ord.getId() %>">Détails</a>

                <form action="livrerOrdonnance" method="post" style="display: inline;">
                    <input type="hidden" name="idOrdonnance" value="<%= ord.getId() %>">
                    <input type="submit" value="Livraison">
                </form>
            </td>
        </tr>
        <% } %>
    <% } %>
</table>
</body>
</html>