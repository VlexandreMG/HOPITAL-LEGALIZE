package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Client;
import utils.DBconnexion;

public class ClientDAO {
    public static Vector<Client> getClient() throws Exception {
        Vector<Client> Clients = new Vector<>();

        String sql = "SELECT ID,NOM FROM Client";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getString("ID"));
                client.setNom(rs.getString("NOM"));
                Clients.add(client);
            }
            return Clients;
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignore) {}
            if (ps != null) try { ps.close(); } catch (Exception ignore) {}
            if (con != null) try { con.close(); } catch (Exception ignore) {}
        }
    }

    public static Client getClientByIdConsultation(String idConsultation) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Client client = null;
    
    try {
        conn = DBconnexion.getConnection();
        String query = "SELECT c.* FROM CLIENT c " +
                      "JOIN MED_CONSULTATION mc ON c.ID = mc.PATIENT " +
                      "WHERE mc.ID = ?";
        
        ps = conn.prepareStatement(query);
        ps.setString(1, idConsultation);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            client = new Client();
            client.setId(rs.getString("ID"));
            client.setNom(rs.getString("NOM"));
            //client.setPrenom(rs.getString("PRENOM"));
            //client.setDateNaissance(rs.getDate("DATE_NAISSANCE"));
            //client.setAdresse(rs.getString("ADRESSE"));
            //client.setTelephone(rs.getString("TELEPHONE"));
            //client.setEmail(rs.getString("EMAIL"));
            // Ajoute les autres propriétés selon ta classe Client
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return client;
}

    public static void main(String[] args) {
        try {
            Vector<Client> Cl = getClient();
            for (Client client : Cl) {
                System.out.println(client);
            }
        } catch (Exception e) {
            
        }

    }

}
