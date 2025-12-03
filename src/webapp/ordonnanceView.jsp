<%@ page import="java.util.Vector" %>
<%@ page import="model.*" %>
<%@ page import="dao.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    <%
    String idOrdonnance = request.getParameter("id");
    MedOrdonnance ordonnance = null;
    Vector<MedOrdonnanceFille> medicaments = new Vector<>();
    
    if (idOrdonnance != null && !idOrdonnance.trim().isEmpty()) {
        ordonnance = MedOrdonnanceDAO.getOrdonnanceById(idOrdonnance);
        medicaments = MedOrdonnanceDAO.getOrdonneFillebyiDOrd(idOrdonnance);
    }
    %>

    <h1>ORDONNANCE MÉDICALE</h1>
    <p>Document médical officiel</p>

<% if (ordonnance != null) { %>

<table>
    <tr>
        <td><strong>N° Ordonnance:</strong></td>
        <td><%= ordonnance.getId() %></td>
        <td><strong>Consultation:</strong></td>
        <td><%= ordonnance.getIdConsultation() %></td>
    </tr>
    <tr>
        <td><strong>Médecin:</strong></td>
        <td><%= ordonnance.getIdMedecin() %></td>
        <td><strong>Durée de traitement:</strong></td>
        <td><%= ordonnance.getNbJours() %> jours</td>
    </tr>
</table>

<h2>Prescriptions Médicales (<%= medicaments.size() %> médicament(s))</h2>

<% if (medicaments.isEmpty()) { %>
    <p>Aucun médicament prescrit pour cette ordonnance.</p>
<% } else { %>
    <% for (int i = 0; i < medicaments.size(); i++) { 
        MedOrdonnanceFille med = medicaments.get(i);
    %>
    <h3>Médicament #<%= i + 1 %></h3>
    
    <table border="1">
        <tr>
            <th>Médicament</th>
            <th>Quantité</th>
            <th>Durée</th>
            <th>Prise en charge</th>
        </tr>
        <tr>
            <td><%= med.getIdMedicament() %></td>
            <td><%= med.getQuantite() %> <%= med.getUnite() != null ? med.getUnite() : "unité(s)" %></td>
            <td><%= med.getNbJours() %> jours</td>
            <td><%= med.getTauxPriseEnCharge() %>%</td>
        </tr>
    </table>
    
    <% if (med.getPosologie() != null && !med.getPosologie().trim().isEmpty()) { %>
    <p><strong>Posologie:</strong> <%= med.getPosologie() %></p>
    <% } %>
    
    <% if (med.getRemarque() != null && !med.getRemarque().trim().isEmpty()) { %>
    <p><strong>Remarques:</strong> <%= med.getRemarque() %></p>
    <% } %>
    
    <hr>
    <% } %>
<% } %>

<% if (ordonnance.getObservation() != null && !ordonnance.getObservation().trim().isEmpty()) { %>
<p><strong>Observations générales:</strong> <%= ordonnance.getObservation() %></p>
<% } %>

<p>Ordonnance établie le : <%= new java.util.Date() %></p>
<p><strong>Signature et cachet du médecin</strong></p>

<% } %>
</body>
</html>