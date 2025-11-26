package dao;

import java.sql.*;
import model.Users;
import utils.DBconnexion;

public class UsersDAO {

    public static Users login(String username, String password) 
        throws Exception {
        
        String query = "SELECT mm.nom nom, mm.prenom prenom, u.LOGINUSER, u.NOMUSER, u.ADRUSER, u.TELUSER, u.IDROLE, u.REFUSER " +
                      "FROM USERS u " +
                      "JOIN MED_MEDECIN mm ON mm.IDUSER = u.REFUSER " +
                      "WHERE u.LOGINUSER = ? AND u.PWDUSER = ?";
        Connection conn = DBconnexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users user = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new Users();
                user.setLogin(rs.getString("LOGINUSER"));
                user.setNom(rs.getString("NOMUSER"));
                user.setAdresse(rs.getString("ADRUSER"));
                user.setTel(rs.getString("TELUSER"));
                user.setIdRole(rs.getString("IDROLE"));
                user.setIdUser(rs.getString("REFUSER")); // Correction: REFUSER au lieu de IDUSER
            } else {
                throw new Exception("Vous n'êtes pas un medecin ou identifiants incorrects.");
            }
            
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la connexion: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return user;
    }
}