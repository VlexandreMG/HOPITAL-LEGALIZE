package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.*;
import utils.*;

public class VenteDAO {

    public static String createVenteFromOrdonnance(String idOrdonnance, Date daty) {

        Vector<MedOrdonnanceFille> allOrd = MedOrdonnanceDAO.getOrdonneFillebyiDOrd(idOrdonnance);
        String idVente = creeVente(idOrdonnance, daty);
        if (idVente != null) {
            for (MedOrdonnanceFille ordFille : allOrd) {
                boolean success = createVenteDetail(idVente, ordFille.getId());
                if (!success) {
                    return null;
                }
            }
            return idVente;
        }

        return null;
    }

    public static boolean createVenteDetail(String idVente, String idOrdonnanceFille) {
        Connection con = null;
        PreparedStatement ps = null;

        try {

            MedOrdonnanceFille ordFille = MedOrdonnanceDAO.getOrdonnanceFilleById(idOrdonnanceFille);

            if (ordFille == null) {
                System.out.println("Ordonnance fille non trouvée: " + idOrdonnanceFille);
                return false;
            }

            String idProduit = ordFille.getIdMedicament();
            int quantite = ordFille.getQuantite();

            con = DBconnexion.getConnection();

            String idVenteDetail = getNextIdVenteDetails(con);

            String sql = "INSERT INTO VENTE_DETAILS (ID, IDVENTE, IDPRODUIT, QTE) VALUES (?, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, idVenteDetail);
            ps.setString(2, idVente);
            ps.setString(3, idProduit);
            ps.setInt(4, quantite);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null)
                try {
                    ps.close();
                } catch (Exception ignore) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignore) {
                }
        }
    }

    public static String getNextIdVente(Connection con) throws Exception {
        String sql = "SELECT GETSEQVENTE() AS ID FROM dual";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String seq = rs.getString("ID");

            String formatted = "VNT" + String.format("%012d", Long.parseLong(seq));

            return formatted;
        }

        throw new Exception("Impossible d'obtenir l'ID de la vente !");
    }

    public static String getNextIdVenteDetails(Connection con) throws Exception {
        String sql = "SELECT GETSEQVENTEDETAILS() AS ID FROM dual";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String seq = rs.getString("ID");

            String formatted = "VTD" + String.format("%012d", Long.parseLong(seq));

            return formatted;
        }

        throw new Exception("Impossible d'obtenir l'ID de la venteDetail !");
    }

    public static String creeVente(String idOrdonnance, Date daty) {

        Connection con = null;
        String venteId = null;

        try {
            con = DBconnexion.getConnection();
            con.setAutoCommit(false);

            MedOrdonnance ord = MedOrdonnanceDAO.getOrdonnanceById(idOrdonnance);

            venteId = getNextIdVente(con);

            String sql = "INSERT INTO VENTE (ID, IDMAGASIN, DATY, ETAT, IDCLIENT) "
                    + "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, venteId);
                ps.setString(2, "PHARM004");
                ps.setDate(3, new java.sql.Date(daty.getTime()));
                ps.setInt(4, 11);
                ps.setString(5, ord.getObservation());

                ps.executeUpdate();
            }

            con.commit();
        } catch (Exception e) {
            try {
                if (con != null)
                    con.rollback();
            } catch (Exception ex) {
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }

        return venteId;
    }

    public static Vector<Vente_detail> getVenteDetailsByIdVente(String idVente) {
        Vector<Vente_detail> details = new Vector<>();
        String sql = "SELECT * FROM VENTE_DETAILS WHERE IDVENTE = ?";
        Connection con = DBconnexion.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idVente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vente_detail detail = new Vente_detail();
                    detail.setId(rs.getString("ID"));
                    detail.setId_vente(rs.getString("IDVENTE"));
                    detail.setId_produit(rs.getString("IDPRODUIT"));
                    detail.setQuantite(rs.getInt("QTE"));
                    details.add(detail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return details;
    }

    public static Vente_detail getVenteDetailById(String idVente_detail) {
        String sql = "SELECT * FROM VENTE_DETAILS WHERE ID = ?";
        Connection con = DBconnexion.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idVente_detail);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Vente_detail detail = new Vente_detail();
                    detail.setId(rs.getString("ID"));
                    detail.setId_vente(rs.getString("IDVENTE"));
                    detail.setId_produit(rs.getString("IDPRODUIT"));
                    detail.setQuantite(rs.getInt("QTE"));
                    return detail;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Vente getVenteById(String idVente) {
        String sql = "SELECT * FROM VENTE WHERE ID = ?";
        Connection con = DBconnexion.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idVente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Vente vente = new Vente();
                    vente.setId(rs.getString("ID"));
                    vente.setId_magasin(rs.getString("IDMAGASIN"));
                    vente.setDate(rs.getDate("DATY"));
                    vente.setEtat(rs.getInt("ETAT"));
                    vente.setId_client(rs.getString("IDCLIENT"));
                    return vente;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String a = createVenteFromOrdonnance("ORD000376", java.sql.Date.valueOf("2024-06-20"));
            System.out.println("ID Vente créée: " + a);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
