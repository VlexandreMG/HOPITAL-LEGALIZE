package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import dao.*;

@WebServlet("/livrerOrdonnance")
public class DeliverOrdonnance extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String idOrdonnance = request.getParameter("idOrdonnance");

        try {
            if (idOrdonnance == null || idOrdonnance.trim().isEmpty()) {
                response.getWriter().println("Erreur: idOrdonnance manquant");
                return;
            }

            // // pass commentaire to DAO
            // String venteId = VenteDAO.createVenteFromOrdonnance(idOrdonnance.trim(),
            // commentaire);
            // if (venteId != null) {
            // response.getWriter().println("Vente créée (" + venteId + ") à partir de
            // l'ordonnance " + idOrdonnance);
            // } else {
            // response.getWriter().println("Erreur lors de la création de la vente");
            // }
            String idvente = VenteDAO.createVenteFromOrdonnance(idOrdonnance.trim(),
                    java.sql.Date.valueOf("2024-06-20"));
            if (idvente != null) {
                response.getWriter().println("Vente créée (" + idvente + ") à partir de l'ordonnance " + idOrdonnance);
                String mvt = Mvt_stockDAO.createMvtFromVente(idvente);
                if (mvt != null) {
                    response.getWriter().println("Mouvement de stock créé (" + mvt + ") pour la vente " + idvente);
                } else {
                    response.getWriter()
                            .println("Erreur lors de la création du mouvement de stock pour la vente " + idvente);
                }
            } else {
                response.getWriter().println("Erreur lors de la création de la vente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erreur: " + e.getMessage());
        }
    }
}
