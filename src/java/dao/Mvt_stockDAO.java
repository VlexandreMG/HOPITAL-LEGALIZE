package dao;

import java.sql.Date;
import java.util.Vector;
import dao.VenteDAO;
import model.Vente_detail;
import model.Vente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.*;

public class Mvt_stockDAO {

    public static String createMvtFromVente(String idVente) {
        Vector<Vente_detail> venteDetails = VenteDAO.getVenteDetailsByIdVente(idVente);
        String idMvt = creeMvt(idVente);
        if (idMvt != null) {
            for (Vente_detail vtf : venteDetails) {
                boolean success = createMvtFille(idMvt, vtf.getId());
                if (!success) {
                    return null;
                }
            }
            return idMvt;
        }
        return null;
    }

    public static boolean createMvtFille(String idMvt, String idVente_detail) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Vente_detail vente_detail = VenteDAO.getVenteDetailById(idVente_detail);
            if (vente_detail == null) {
                System.out.println("Vente fille non trouvée: " + idVente_detail);
                return false;
            }

            String idProduit = vente_detail.getId_produit();
            int quantite = vente_detail.getQuantite();

            con = DBconnexion.getConnection();
            String idMvtFille = getNextIdMouvementFille(con);

            String sql = "INSERT INTO MVTSTOCKFILLE (ID, IDMVTSTOCK, IDPRODUIT, SORTIE,IDVENTEDETAIL,ENTREE) VALUES (?, ?, ?, ?,?,0)";
            ps = con.prepareStatement(sql);
            ps.setString(1, idMvtFille);
            ps.setString(2, idMvt);
            ps.setString(3, idProduit);
            ps.setInt(4, quantite);
            ps.setString(5, idVente_detail);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            System.out.println("Erreur lors de la création du mouvement fille: " + e.getMessage());
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

    private static String getNextIdMouvement(Connection con) throws Exception {
        String sql = "SELECT GETSEQMVTSTOCK() AS ID FROM dual";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                String seq = rs.getString("ID");
                return "MVTST" + String.format("%012d", Long.parseLong(seq));
            }
            throw new Exception("Impossible d'obtenir l'ID de mouvement !");
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception ignore) {
                }
            if (ps != null)
                try {
                    ps.close();
                } catch (Exception ignore) {
                }
            // NE PAS fermer la connexion ici
        }
    }

    private static String getNextIdMouvementFille(Connection con) throws Exception {
        String sql = "SELECT GETSEQMVTSTOCKFILLE() AS ID FROM dual";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                String seq = rs.getString("ID");
                return "MVTSFI" + String.format("%012d", Long.parseLong(seq));
            }
            throw new Exception("Impossible d'obtenir l'ID de mouvement fille !");
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception ignore) {
                }
            if (ps != null)
                try {
                    ps.close();
                } catch (Exception ignore) {
                }
            // NE PAS fermer la connexion ici
        }
    }

    public static String creeMvt(String idVente) {
        Connection con = null;
        String mvtId = null;

        try {
            con = DBconnexion.getConnection();
            con.setAutoCommit(false);

            Vente vente = VenteDAO.getVenteById(idVente);
            if (vente == null) {
                System.out.println("Vente non trouvée: " + idVente);
                return null;
            }

            Date date = vente.getDate();
            mvtId = getNextIdMouvement(con);

            String sql = "INSERT INTO MVTSTOCK (ID, IDVENTE, DATY, ETAT,IDMAGASIN) VALUES (?, ?, ?, 11 ,'PHARM004')";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, mvtId);
                ps.setString(2, idVente);
                ps.setDate(3, date);
                ps.executeUpdate();
            }

            con.commit();
            return mvtId;

        } catch (Exception e) {
            System.out.println("Erreur lors de la création du mouvement de stock pour la vente " + idVente + ": "
                    + e.getMessage());
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                }
            }
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        String idVente = "VNT000000003625";
        String idMvt = createMvtFromVente(idVente);
        if (idMvt != null) {
            System.out.println("Mouvement de stock créé avec succès: " + idMvt);
        } else {
            System.out.println("Échec de la création du mouvement de stock pour la vente: " + idVente);
        }
    }
}