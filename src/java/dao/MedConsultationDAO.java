package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.*;

public class MedConsultationDAO {

    // Récupérer toutes les consultations
    public static List<MedConsultation> getAllConsultations() throws SQLException {
        String query = "SELECT * FROM MED_CONSULTATION ORDER BY DATY DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MedConsultation> consultations = new ArrayList<>();
        
        try {
            conn = DBconnexion.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                MedConsultation consultation = mapResultSetToConsultation(rs);
                consultations.add(consultation);
            }
        } finally {
            closeResources(rs, ps, conn);
        }
        
        return consultations;
    }

    // Récupérer les consultations par ID médecin
    public static List<MedConsultation> getByIdMedecin(String idMedecin) throws SQLException {
    String query = "SELECT *  FROM MED_CONSULTATION WHERE IDMEDECIN = ? ORDER BY DATY DESC";
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<MedConsultation> consultations = new ArrayList<>();
    
    try {
        conn = DBconnexion.getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, idMedecin);
        rs = ps.executeQuery();

        // Debug: afficher les colonnes disponibles (APRES executeQuery mais AVANT rs.next())
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println("Colonnes disponibles:");
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(i + ": " + metaData.getColumnName(i));
        }
        
        while (rs.next()) {
            MedConsultation consultation = mapResultSetToConsultation(rs);
            consultations.add(consultation);
        }
    } catch (SQLException e) {
        System.out.println("Erreur SQL dans getByIdMedecin: " + e.getMessage());
        e.printStackTrace();
        throw e;
    } finally {
        closeResources(rs, ps, conn);
    }
    
    return consultations;
}

    // Récupérer une consultation par son ID
    public static MedConsultation getById(String id) throws SQLException {
        String query = "SELECT * FROM MED_CONSULTATION WHERE ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MedConsultation consultation = null;
        
        try {
            conn = DBconnexion.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                consultation = mapResultSetToConsultation(rs);
            }
        } finally {
            closeResources(rs, ps, conn);
        }
        
        return consultation;
    }

    // Méthode utilitaire pour mapper le ResultSet vers l'objet MedConsultation
    private static MedConsultation mapResultSetToConsultation(ResultSet rs) throws SQLException {
        MedConsultation consultation = new MedConsultation();
        
        consultation.setId(rs.getString("ID"));
        consultation.setDaty(rs.getDate("DATY"));
        consultation.setIdDemande(rs.getString("ID_DEMANDE"));
        consultation.setIdMedecin(rs.getString("IDMEDECIN"));
        consultation.setDescription(rs.getString("DESCRIPTION"));
        consultation.setNiveauMaladie(rs.getString("NIVEAU_MALADIE"));
        consultation.setHeureArrivee(rs.getString("HEURE_ARRIVEE"));
        consultation.setHeureDepart(rs.getString("HEURE_DEPART"));
        consultation.setPatient(rs.getString("PATIENT"));
        consultation.setEtat(rs.getInt("ETAT"));
        consultation.setEntenne(rs.getString("ENTENNE"));
        consultation.setIdHospitalisation(rs.getString("IDHOSPITALISATION"));
        consultation.setService(rs.getString("SERVICE"));
        consultation.setCategorieMaladie(rs.getString("CATEGORIEMALADIE"));
        consultation.setPriseEnCharge(rs.getString("PRISEENCHARGE"));
        consultation.setTauxPriseEnCharge(rs.getDouble("TAUXPRISEENCHARGE"));
        consultation.setIdSortie(rs.getString("IDSORTIE"));
        consultation.setHistoireMaladie(rs.getString("HISTOIREMALADIE"));
        consultation.setDiagnostiquePog(rs.getString("DIAGNOSTICPDG"));
        
        return consultation;
    }

    // Méthode utilitaire pour fermer les ressources
    private static void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
