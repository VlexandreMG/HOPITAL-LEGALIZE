<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<% 
Vector<MedOrdonnance> ordonnancesMere = new Vector<>();
String idConsultation = request.getParameter("idConsultation");
session.setAttribute("idConsultation", idConsultation);
ordonnancesMere = MedOrdonnanceDAO.getOrdonnanceByIdCLient(idConsultation);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ordonnance</title>
</head>
<body>
    <%= idConsultation %>
    <h2>Ordonnance pour le patient : </h2>

    <h2>Test des ordonnances</h2>
<p>ID Consultation: <%= idConsultation %></p>
<p>Nombre d'ordonnances: <%= ordonnancesMere.size() %></p>

<% for (MedOrdonnance ord : ordonnancesMere) { %>
    <div style="border: 1px solid #ccc; margin: 10px; padding: 10px;">
        <p>ID: <%= ord.getId() %></p>
        <p>Médecin: <%= ord.getIdMedecin() %></p>
        <p>Jours: <%= ord.getNbJours() %></p>
        <p>Observation: <%= ord.getObservation() %></p>
    </div>
<% } %>

<a href="accueil.jsp">Retour</a>
</body>
</html>