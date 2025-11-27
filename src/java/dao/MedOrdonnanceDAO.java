package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import model.*;
import utils.*;

public class MedOrdonnanceDAO {

    public static String createOrdonnance(MedOrdonnance ordonnance) throws Exception {
        String sql = "INSERT INTO MED_ORDONNANCE (ID, ID_CONSULTATION, IDMEDECIN, NB_JOURS, OBSERVATION_SOINS) VALUES (?, ?, ?, ?, ?)";

        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);

            String generatedId = generateOrdonnanceId(con);
            ps.setString(1, generatedId);
            ps.setString(2, ordonnance.getIdConsultation());
            ps.setString(3, ordonnance.getIdMedecin());
            ps.setInt(4, ordonnance.getNbJours());
            ps.setString(5, ordonnance.getObservation());

            int updated = ps.executeUpdate();
            return updated > 0 ? generatedId : null;
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

    public static boolean addMedicament(MedOrdonnanceFille medicament) throws Exception {
        String sql = "INSERT INTO MED_ORDONNANCE_FILLE (ID, IDORDONNANCE, IDMEDICAMENT, POSOLOGIE, QUANTITE, UNITE, NB_JOURS, TAUXPRISEENCHARGE, REMARQUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, generateOrdonnanceFilleId(con));
            ps.setString(2, medicament.getIdOrdonnance());
            ps.setString(3, medicament.getIdMedicament());
            ps.setString(4, medicament.getPosologie());
            ps.setInt(5, medicament.getQuantite());
            ps.setString(6, medicament.getUnite());
            ps.setInt(7, medicament.getNbJours());
            ps.setDouble(8, medicament.getTauxPriseEnCharge());
            ps.setString(9, medicament.getRemarque());

            return ps.executeUpdate() > 0;
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

    private static String generateOrdonnanceId(Connection con) throws Exception {
        String maxIdSql = "SELECT MAX(ID) AS max_id FROM MED_ORDONNANCE";
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            ps = con.prepareStatement(maxIdSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String maxId = rs.getString("max_id");
                if (maxId != null && maxId.startsWith("ORD")) {
                    String numbers = maxId.substring(3);
                    int nextNum = Integer.parseInt(numbers) + 1;
                    return "ORD" + String.format("%06d", nextNum);
                }
            }
            return "ORD000001";
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
        }
    }

    public static String generateOrdonnanceId1() throws Exception {
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            con = DBconnexion.getConnection();
            String maxIdSql = "SELECT MAX(ID) AS max_id FROM MED_ORDONNANCE";
            ps = con.prepareStatement(maxIdSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String maxId = rs.getString("max_id");
                if (maxId != null && maxId.startsWith("ORD")) {
                    String numbers = maxId.substring(3);
                    int nextNum = Integer.parseInt(numbers) + 1;
                    return "ORD" + String.format("%06d", nextNum);
                }
            }
            return "ORD000001";
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
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignore) {
                }
        }
    }

    private static String generateOrdonnanceFilleId(Connection con) throws Exception {
        String maxIdSql = "SELECT MAX(ID) AS max_id FROM MED_ORDONNANCE_FILLE";
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            ps = con.prepareStatement(maxIdSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String maxId = rs.getString("max_id");
                if (maxId != null && maxId.startsWith("ORDF")) {
                    String numbers = maxId.substring(4);
                    int nextNum = Integer.parseInt(numbers) + 1;
                    return "ORDF" + String.format("%06d", nextNum);
                }
            }
            return "ORDF000001";
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
        }
    }

    public static Vector<String> getConsultations() throws Exception {
        Vector<String> consultations = new Vector<>();
        String sql = "SELECT ID, PATIENT FROM MED_CONSULTATION ORDER BY DATY DESC";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                consultations.add(rs.getString("ID") + " - " + rs.getString("PATIENT"));
            }
            return consultations;
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
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignore) {
                }
        }
    }

    public static Vector<String> getMedicaments() throws Exception {
        Vector<String> medicaments = new Vector<>();
        String sql = "SELECT ID, LIBELLE FROM AS_INGREDIENTS ORDER BY LIBELLE";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                medicaments.add(rs.getString("ID") + " - " + rs.getString("LIBELLE"));
            }
            return medicaments;
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
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignore) {
                }
        }
    }

    public static Vector getOrdonneFillebyiDOrd(String idrrr) {
        Vector<MedOrdonnanceFille> od = new Vector<>();
        String sql = "select * from  MED_ORDONNANCE_FILLE WHERE IDORDONNANCE = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idrrr);
            rs = ps.executeQuery();
            while (rs.next()) {
                MedOrdonnanceFille c = new MedOrdonnanceFille();
                c.setId(rs.getString("ID"));
                c.setIdOrdonnance(rs.getString("IDORDONNANCE"));
                c.setIdMedicament(rs.getString("IDMEDICAMENT"));
                c.setPosologie(rs.getString("POSOLOGIE"));
                c.setQuantite(rs.getInt("QUANTITE"));
                c.setUnite(rs.getString("UNITE"));
                c.setTauxPriseEnCharge(rs.getDouble("TAUXPRISEENCHARGE"));
                c.setRemarque(rs.getString("REMARQUE"));
                c.setNbJours(rs.getInt("NB_JOURS"));
                od.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignore) {
                }
        }
        return od;
    }

   public static MedOrdonnance getOrdonnanceById(String idOrdonnance) {
    MedOrdonnance ordonnance = null;
    String sql = "SELECT * FROM MED_ORDONNANCE WHERE ID = ?";
    java.sql.Connection con = null;
    java.sql.PreparedStatement ps = null;
    java.sql.ResultSet rs = null;
    try {
        con = DBconnexion.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, idOrdonnance);
        rs = ps.executeQuery();
        if (rs.next()) {
            ordonnance = new MedOrdonnance();
            ordonnance.setId(rs.getString("ID"));
            ordonnance.setIdConsultation(rs.getString("ID_CONSULTATION"));
            ordonnance.setIdMedecin(rs.getString("IDMEDECIN"));
            
            // Vérification pour NB_JOURS
            Object nbJoursObj = rs.getObject("NB_JOURS");
            if (nbJoursObj != null) {
                if (nbJoursObj instanceof Number) {
                    ordonnance.setNbJours(((Number) nbJoursObj).intValue());
                } else {
                    try {
                        ordonnance.setNbJours(Integer.parseInt(nbJoursObj.toString()));
                    } catch (NumberFormatException e) {
                        ordonnance.setNbJours(0); 
                    }
                }
            } else {
                ordonnance.setNbJours(0);
            }
            
            ordonnance.setObservation(rs.getString("OBSERVATION_SOINS"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) try { rs.close(); } catch (Exception ignore) {}
        if (ps != null) try { ps.close(); } catch (Exception ignore) {}
        if (con != null) try { con.close(); } catch (Exception ignore) {}
    }
    return ordonnance;
}

public static Vector<MedOrdonnance> getOrdonnanceByIdCLient(String idClient) {
        Vector<MedOrdonnance> ordonnances = new Vector<>();
        String sql = "SELECT MO.* FROM MED_ORDONNANCE MO JOIN MED_CONSULTATION MC ON MO.ID_CONSULTATION = MC.ID WHERE MC.PATIENT = ? ORDER BY MO.ID DESC";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idClient);
            rs = ps.executeQuery();
            while (rs.next()) {
                MedOrdonnance ordonnance = new MedOrdonnance();
                ordonnance.setId(rs.getString("ID"));
                ordonnance.setIdConsultation(rs.getString("ID_CONSULTATION"));
                ordonnance.setIdMedecin(rs.getString("IDMEDECIN"));
                ordonnance.setNbJours(rs.getInt("NB_JOURS"));
                ordonnance.setObservation(rs.getString("OBSERVATION_SOINS"));
                ordonnances.add(ordonnance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignore) {}
            if (ps != null) try { ps.close(); } catch (Exception ignore) {}
            if (con != null) try { con.close(); } catch (Exception ignore) {}
        }
        return ordonnances;
    }

    public static Vector<MedOrdonnance> getAllOrdonnances() {
        Vector<MedOrdonnance> ordonnances = new Vector<>();
        String sql = "SELECT * FROM MED_ORDONNANCE ORDER BY ID DESC";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MedOrdonnance ordonnance = new MedOrdonnance();
                ordonnance.setId(rs.getString("ID"));
                ordonnance.setIdConsultation(rs.getString("ID_CONSULTATION"));
                ordonnance.setIdMedecin(rs.getString("IDMEDECIN"));
                ordonnance.setNbJours(rs.getInt("NB_JOURS"));
                ordonnance.setObservation(rs.getString("OBSERVATION_SOINS"));
                ordonnances.add(ordonnance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignore) {
                }
        }
        return ordonnances;
    }

    public static MedOrdonnanceFille getOrdonnanceFilleById(String idOrdonnanceFille) {
        MedOrdonnanceFille ordonnanceFille = null;
        String sql = "SELECT * FROM MED_ORDONNANCE_FILLE WHERE ID = ?";

        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;

        try {
            con = DBconnexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idOrdonnanceFille);
            rs = ps.executeQuery();

            if (rs.next()) {
                ordonnanceFille = new MedOrdonnanceFille();
                ordonnanceFille.setId(rs.getString("ID"));
                ordonnanceFille.setIdOrdonnance(rs.getString("IDORDONNANCE"));
                ordonnanceFille.setIdMedicament(rs.getString("IDMEDICAMENT"));
                ordonnanceFille.setPosologie(rs.getString("POSOLOGIE"));
                ordonnanceFille.setQuantite(rs.getInt("QUANTITE"));
                ordonnanceFille.setUnite(rs.getString("UNITE"));
                ordonnanceFille.setNbJours(rs.getInt("NB_JOURS"));
                ordonnanceFille.setTauxPriseEnCharge(rs.getDouble("TAUXPRISEENCHARGE"));
                ordonnanceFille.setRemarque(rs.getString("REMARQUE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignore) {
                }
        }

        return ordonnanceFille;
    }
}