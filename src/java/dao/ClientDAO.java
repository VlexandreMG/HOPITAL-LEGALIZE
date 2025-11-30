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

    // public static void main(String[] args) {
    //     try {
    //         Vector<Client> Cl = getClient();
    //         for (Client client : Cl) {
    //             System.out.println(client);
    //         }
    //     } catch (Exception e) {
            
    //     }

    // }

    public static String generateId() throws Exception {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = DBconnexion.getConnection();
        
        // 1. Trouver le plus grand ID numérique
        String query = "SELECT ID FROM CLIENT WHERE ID LIKE 'CLI%' ORDER BY ID DESC";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        
        int maxNumber = 0;
        
        while (rs.next()) {
            String id = rs.getString("ID");
            System.err.println("ID examiné: " + id);
            
            if (id.startsWith("CLI")) {
                String numbers = id.substring(3);
                StringBuilder cleanNumbers = new StringBuilder();
                
                for (char c : numbers.toCharArray()) {
                    if (Character.isDigit(c)) {
                        cleanNumbers.append(c);
                    }
                }
                
                if (cleanNumbers.length() > 0) {
                    try {
                        int currentNum = Integer.parseInt(cleanNumbers.toString());
                        if (currentNum > maxNumber) {
                            maxNumber = currentNum;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorer les IDs mal formés
                    }
                }
            }
        }
        
        // 2. Générer le prochain ID
        int nextNum = maxNumber + 1;
        String newId = "CLI" + String.format("%06d", nextNum);
        
        System.err.println("=== DEBUG GENERATE ID ===");
        System.err.println("MAX numérique trouvé: " + maxNumber);
        System.err.println("NOUVEL ID généré: " + newId);
        System.err.println("=== FIN DEBUG ===");
        
        return newId;
        
    } catch (Exception e) {
        throw new Exception("Erreur lors de la génération de l'ID du client: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ignore) {}
    }
}

    public static void addClient(Client client) throws Exception {
        String querry = "INSERT INTO CLIENT (ID, NOM, TELEPHONE) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBconnexion.getConnection();
            ps = conn.prepareStatement(querry);
            String newId = generateId();
            client.setId(newId);
            ps.setString(1, client.getId());
            ps.setString(2, client.getNom());
            ps.setString(3, client.getTelephone());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'ajout du client: " + e.getMessage());
        } finally {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
        }
    }   
}