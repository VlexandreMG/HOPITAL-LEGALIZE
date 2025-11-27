package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
