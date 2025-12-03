package model;

import java.sql.Date;

public class Vente {

    String id;
    String id_magasin;
    Date daty;
    int etat;
    String id_client;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_magasin() {
        return id_magasin;
    }

    public void setId_magasin(String id_magasin) {
        this.id_magasin = id_magasin;
    }

    public Date getDate() {
        return daty;
    }

    public void setDate(Date date) {
        this.daty = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Vente [id=" + id + ", id_magasin=" + id_magasin + ", date=" + daty + ", etat=" + etat + ", id_client="
                + id_client + "]";
    }
}