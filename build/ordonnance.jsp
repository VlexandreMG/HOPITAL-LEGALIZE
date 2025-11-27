<% 
String idConsultation = request.getParameter("idConsultation");
session.setAttribute("idConsultation", idConsultation);
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
</body>
</html>