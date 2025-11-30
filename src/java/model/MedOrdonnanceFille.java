package model;

public class MedOrdonnanceFille {
    private String id;
    private String idOrdonnance;
    private String idMedicament;
    private String posologie;
    private int quantite;
    private String unite;
    private int nbJours;
    private double tauxPriseEnCharge;
    private String remarque;

    public MedOrdonnanceFille() {
    }

    public MedOrdonnanceFille(String id, String idOrdonnance, String idMedicament, String posologie,
        int quantite, String unite, int nbJours, double tauxPriseEnCharge, String remarque) {
        this.id = id;
        this.idOrdonnance = idOrdonnance;
        this.idMedicament = idMedicament;
        this.posologie = posologie;
        this.quantite = quantite;
        this.unite = unite;
        this.nbJours = nbJours;
        this.tauxPriseEnCharge = tauxPriseEnCharge;
        this.remarque = remarque;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOrdonnance() {
        return idOrdonnance;
    }

    public void setIdOrdonnance(String idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
    }

    public String getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(String idMedicament) {
        this.idMedicament = idMedicament;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    public double getTauxPriseEnCharge() {
        return tauxPriseEnCharge;
    }

    public void setTauxPriseEnCharge(double tauxPriseEnCharge) {
        this.tauxPriseEnCharge = tauxPriseEnCharge;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    @Override
    public String toString() {
        return "OrdonnanceFille [id=" + id + ", idOrdonnance=" + idOrdonnance + ", idMedicament=" + idMedicament
                + ", posologie=" + posologie + ", quantite=" + quantite + ", unite=" + unite + ", nbJours=" + nbJours
                + ", tauxPriseEnCharge=" + tauxPriseEnCharge + ", remarque=" + remarque + "]";
    }

}