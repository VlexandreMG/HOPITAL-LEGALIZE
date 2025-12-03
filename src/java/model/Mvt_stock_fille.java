package model;

public class Mvt_stock_fille {
    String id;
    String id_mvt_stock;
    String id_produit;
    int quantite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_mvt_stock() {
        return id_mvt_stock;
    }

    public void setId_mvt_stock(String id_mvt_stock) {
        this.id_mvt_stock = id_mvt_stock;
    }

    public String getId_produit() {
        return id_produit;
    }

    public void setId_produit(String id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
