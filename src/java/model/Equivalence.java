package model;

public class Equivalence {
    String id;
    String idProduit;
    String unite;
    int quantite;
    double prixUniutaire;
    String nomUnite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public double getPrixUniutaire() {
        return prixUniutaire;
    }

    public void setPrixUniutaire(double prixUniutaire) {
        this.prixUniutaire = prixUniutaire;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public Equivalence(){}

    @Override
    public String toString() {
        return "Equivalence{" +
                "id='" + id + '\'' +
                ", idProduit='" + idProduit + '\'' +
                ", unite='" + unite + '\'' +
                ", quantite=" + quantite +
                ", prixUniutaire=" + prixUniutaire +
                ", nomUnite='" + nomUnite + '\'' +
                '}';
    }
}
