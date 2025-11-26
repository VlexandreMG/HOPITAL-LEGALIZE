<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%
String user = request.getParameter("user");
String password = request.getParameter("pwd");
Users utilisateur = null;

try {
    utilisateur = UsersDAO.login(user, password);
    if(utilisateur != null) {  
        session.setAttribute("utilisateur", utilisateur);
        response.sendRedirect("accueil.jsp");
    }
} catch (Exception e) {
    response.sendRedirect("index.jsp?error=" + java.net.URLEncoder.encode(e.getMessage(), "UTF-8"));
}
%>