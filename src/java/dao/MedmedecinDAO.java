
package dao;

import java.sql.*;
import model.*;
import utils.*;

public class MedmedecinDAO {
    
    public static Medmedecin getByIdUser(String idUser) throws SQLException {
        String query = "SELECT * FROM MED_MEDECIN WHERE IDUSER = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Medmedecin medecin = null;
        
        try {
            conn = DBconnexion.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idUser);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                medecin = new Medmedecin();
                medecin.setId(rs.getString("ID"));
                medecin.setNom(rs.getString("NOM"));
                medecin.setPrenom(rs.getString("PRENOM"));
                medecin.setMatricule(rs.getString("MATRICULE"));
                medecin.setTelephone(rs.getString("TELEPHONE"));
                medecin.setEmail(rs.getString("EMAIL"));
                medecin.setNiveau(rs.getString("NIVEAU"));
                medecin.setTarifHoraire(rs.getDouble("TARIF_HORAIRE"));
                medecin.setCentre(rs.getString("CENTRE"));
                medecin.setNiveauRole(rs.getInt("NIVEAUROLE"));
                medecin.setIdUser(rs.getInt("IDUSER"));
                medecin.setPourcentage(rs.getDouble("POURCENTAGE"));
                medecin.setProfile(rs.getString("PROFILE"));
            }
        } finally {
            // Fermer les ressources
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return medecin;
    }
}