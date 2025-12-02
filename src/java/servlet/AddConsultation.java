package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import model.*;
import dao.*;

@WebServlet("/addConsultation")

public class AddConsultation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Medmedecin medecin = (Medmedecin) session.getAttribute("medecin");

            String id = MedConsultationDAO.generateId();
            java.sql.Date daty = java.sql.Date.valueOf(request.getParameter("daty"));
            String description = request.getParameter("description");
            String heureArrivee = request.getParameter("heure_arrivee");
            String heureDepart = request.getParameter("heure_depart");
            
            MedConsultation consultation = new MedConsultation();
            consultation.setDaty(daty);
            consultation.setDescription(description);
            consultation.setHeureArrivee(heureArrivee);
            consultation.setHeureDepart(heureDepart);
            consultation.setPatient(id);
            consultation.setIdMedecin(medecin.getId());
            MedConsultationDAO.addConsultation(consultation);
            response.getWriter().println("Consultation ajoutée avec succès !");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'ajout de la consultation", e);
        }
    }
}