package model;

import java.util.Date;

public class MedConsultation {
    private String id;
    private Date daty;
    private String idDemande;
    private String idMedecin;
    private String description;
    private String niveauMaladie;
    private String heureArrivee;
    private String heureDepart;
    private String patient;
    private int etat;
    private String entenne;
    private String idHospitalisation;
    private String service;
    private String categorieMaladie;
    private String priseEnCharge;
    private double tauxPriseEnCharge;
    private String idSortie;
    private String histoireMaladie;
    private String diagnostiquePog;

    // Constructeurs
    public MedConsultation() {
    }

    public MedConsultation(String id, Date daty, String idDemande, String idMedecin, 
                          String description, String niveauMaladie, String heureArrivee, 
                          String heureDepart, String patient, int etat, String entenne, 
                          String idHospitalisation, String service, String categorieMaladie, 
                          String priseEnCharge, double tauxPriseEnCharge, String idSortie, 
                          String histoireMaladie, String diagnostiquePog) {
        this.id = id;
        this.daty = daty;
        this.idDemande = idDemande;
        this.idMedecin = idMedecin;
        this.description = description;
        this.niveauMaladie = niveauMaladie;
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
        this.patient = patient;
        this.etat = etat;
        this.entenne = entenne;
        this.idHospitalisation = idHospitalisation;
        this.service = service;
        this.categorieMaladie = categorieMaladie;
        this.priseEnCharge = priseEnCharge;
        this.tauxPriseEnCharge = tauxPriseEnCharge;
        this.idSortie = idSortie;
        this.histoireMaladie = histoireMaladie;
        this.diagnostiquePog = diagnostiquePog;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(String idDemande) {
        this.idDemande = idDemande;
    }

    public String getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(String idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveauMaladie() {
        return niveauMaladie;
    }

    public void setNiveauMaladie(String niveauMaladie) {
        this.niveauMaladie = niveauMaladie;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getEntenne() {
        return entenne;
    }

    public void setEntenne(String entenne) {
        this.entenne = entenne;
    }

    public String getIdHospitalisation() {
        return idHospitalisation;
    }

    public void setIdHospitalisation(String idHospitalisation) {
        this.idHospitalisation = idHospitalisation;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCategorieMaladie() {
        return categorieMaladie;
    }

    public void setCategorieMaladie(String categorieMaladie) {
        this.categorieMaladie = categorieMaladie;
    }

    public String getPriseEnCharge() {
        return priseEnCharge;
    }

    public void setPriseEnCharge(String priseEnCharge) {
        this.priseEnCharge = priseEnCharge;
    }

    public double getTauxPriseEnCharge() {
        return tauxPriseEnCharge;
    }

    public void setTauxPriseEnCharge(double tauxPriseEnCharge) {
        this.tauxPriseEnCharge = tauxPriseEnCharge;
    }

    public String getIdSortie() {
        return idSortie;
    }

    public void setIdSortie(String idSortie) {
        this.idSortie = idSortie;
    }

    public String getHistoireMaladie() {
        return histoireMaladie;
    }

    public void setHistoireMaladie(String histoireMaladie) {
        this.histoireMaladie = histoireMaladie;
    }

    public String getDiagnostiquePog() {
        return diagnostiquePog;
    }

    public void setDiagnostiquePog(String diagnostiquePog) {
        this.diagnostiquePog = diagnostiquePog;
    }

    @Override
    public String toString() {
        return "MedConsultation{" +
                "id='" + id + '\'' +
                ", daty=" + daty +
                ", idDemande='" + idDemande + '\'' +
                ", idMedecin='" + idMedecin + '\'' +
                ", description='" + description + '\'' +
                ", niveauMaladie='" + niveauMaladie + '\'' +
                ", heureArrivee='" + heureArrivee + '\'' +
                ", heureDepart='" + heureDepart + '\'' +
                ", patient='" + patient + '\'' +
                ", etat=" + etat +
                ", entenne='" + entenne + '\'' +
                ", idHospitalisation='" + idHospitalisation + '\'' +
                ", service='" + service + '\'' +
                ", categorieMaladie='" + categorieMaladie + '\'' +
                ", priseEnCharge='" + priseEnCharge + '\'' +
                ", tauxPriseEnCharge=" + tauxPriseEnCharge +
                ", idSortie='" + idSortie + '\'' +
                ", histoireMaladie='" + histoireMaladie + '\'' +
                ", diagnostiquePog='" + diagnostiquePog + '\'' +
                '}';
    }
}