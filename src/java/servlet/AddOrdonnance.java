package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import model.*;
import dao.*;

@WebServlet("/addOrdonnance")
public class AddOrdonnance extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("utilisateur");
            Medmedecin medecin = (Medmedecin) session.getAttribute("medecin");
            String consultation = (String) session.getAttribute("idConsultation");

            // System.err.println("\n");
            // System.out.println("Consultation ID: " + consultation);            System.out.println("Consultation ID: " + consultation);
            // System.out.println("User : " + user.getIdUser());
            // System.err.println("\n");

            java.sql.Date date = java.sql.Date.valueOf(request.getParameter("daty"));
            int duree = Integer.parseInt(request.getParameter("duree"));

            // System.out.println("Date: " + date);
            // System.out.println("Duration: " + duree);
            // System.err.println("\n");

            if (consultation == null) {
                throw new Exception("La consultation est obligatoire");
            }

            MedOrdonnance ordonnance = new MedOrdonnance();
            ordonnance.setIdConsultation(consultation.split(" - ")[0]);
            System.out.println("Ordonnance Consultation ID: " + ordonnance.getIdConsultation());
            ordonnance.setIdMedecin(medecin.getMatricule());
            System.out.println("Ordonnance Medecin ID: " + ordonnance.getIdMedecin());
            ordonnance.setDaty(date);
            System.out.println("Ordonnance Date: " + ordonnance.getDaty());
            ordonnance.setNbJours(duree);
            System.out.println("Ordonnance Duration: " + ordonnance.getNbJours());

            String idOrdonnance = MedOrdonnanceDAO.addOrdonnance(ordonnance);
            if (idOrdonnance == null) {
                throw new Exception("Erreur lors de la création de l'ordonnance (aucun ID généré).");
            }

            String[] idMedicaments = request.getParameterValues("idmedicament[]");
            String[] posologies = request.getParameterValues("posologie[]");
            String[] quantites = request.getParameterValues("quantite[]");
            String[] unites = request.getParameterValues("unite[]");
            String[] nbJoursMed = request.getParameterValues("nb_jours_med[]");
            String[] taux = request.getParameterValues("taux[]");
            String[] remarques = request.getParameterValues("remarque[]");

            boolean successFille = true;
            if (idMedicaments != null) {
                for (int i = 0; i < idMedicaments.length; i++) {
                    if (idMedicaments[i] != null && !idMedicaments[i].isEmpty()) {
                        MedOrdonnanceFille medicament = new MedOrdonnanceFille();
                        medicament.setIdOrdonnance(idOrdonnance);
                        medicament.setIdMedicament(idMedicaments[i].split(" - ")[0]);
                        medicament.setPosologie(
                                posologies != null && i < posologies.length && posologies[i] != null ? posologies[i]
                                        : "");
                        medicament.setQuantite(quantites != null && i < quantites.length && quantites[i] != null
                                && !quantites[i].isEmpty() ? Integer.parseInt(quantites[i]) : 0);
                        medicament.setUnite(unites != null && i < unites.length && unites[i] != null ? unites[i] : "");
                        medicament.setNbJours(nbJoursMed != null && i < nbJoursMed.length && nbJoursMed[i] != null
                                && !nbJoursMed[i].isEmpty() ? Integer.parseInt(nbJoursMed[i]) : 0);
                        medicament.setTauxPriseEnCharge(
                                taux != null && i < taux.length && taux[i] != null && !taux[i].isEmpty()
                                        ? Double.parseDouble(taux[i])
                                        : 0.0);
                        medicament.setRemarque(
                                remarques != null && i < remarques.length && remarques[i] != null ? remarques[i] : "");

                        if (!MedOrdonnanceDAO.addMedicament(medicament)) {
                            successFille = false;
                        }
                    }
                }
            }

            if (successFille) {
                response.getWriter().println("Ordonnance créée avec succès !");
            } else {
                response.getWriter().println("Ordonnance créée mais certains médicaments n'ont pas pu être ajoutés");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(" Erreur: " + e.getMessage());
        }
    }
}