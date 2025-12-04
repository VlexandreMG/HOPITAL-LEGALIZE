package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnexion {

    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:oracle:thin:@localhost:1521:EE";
        String user = "balou";
        String password = "balou";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println(" Connexion réussie à Oracle !");
        } catch (ClassNotFoundException e) {
            //System.out.println("introuvable !");
            e.printStackTrace();
        } catch (SQLException e) {
            //System.out.println("erreur de connexion à la base Oracle !");
            e.printStackTrace();
        }
        return conn;
    }

    // public static void main(String[] args) throws Exception {
       
    //     Connection c = getConnection();
    //    if (c != null) {
    //        System.out.println("Connexion établie avec succès.");
    //    } else {
    //        System.out.println("Échec de la connexion.");
    //    }

    // }
}
