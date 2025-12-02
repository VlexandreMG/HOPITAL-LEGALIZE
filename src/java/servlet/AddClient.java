package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import model.*;
import dao.*;

@WebServlet("/addClient")

public class AddClient extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String telephone = request.getParameter("telephone");
            Client client = new Client();
            client.setNom(nom);
            client.setTelephone(telephone);
            ClientDAO.addClient(client);
            response.sendRedirect("createConsultation.jsp");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'ajout du client", e);
        }
    }
}