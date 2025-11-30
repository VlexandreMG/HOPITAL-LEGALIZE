package model;

public class MedOrdonnance {
    private String id;
    private String idConsultation;
    private String idMedecin;
    private int nbJours;
    private String observation;
    private java.sql.Date daty;
    
    // Nouveaux attributs ajoutés
    private String idTypeArret;
    private java.sql.Date dateDebut;
    private java.sql.Date dateFin;
    private String idTypeSoins;
    private String type;
    private int etat;
    private String observationSoins;
    private String identite;
    private String idRetraite;
    private String idDeces;
    private String idMembre;
    private String societePriseEncharge;

    public MedOrdonnance() {}

    // Constructeur existant conservé
    public MedOrdonnance(String id, String idConsultation, String idMedecin, int nbJours, String observation, java.sql.Date daty) {
        this.id = id;
        this.idConsultation = idConsultation;
        this.idMedecin = idMedecin;
        this.nbJours = nbJours;
        this.observation = observation;
        this.daty = daty;
    }

    // Getters et Setters existants conservés
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdConsultation() { return idConsultation; }
    public void setIdConsultation(String idConsultation) { this.idConsultation = idConsultation; }

    public String getIdMedecin() { return idMedecin; }
    public void setIdMedecin(String idMedecin) { this.idMedecin = idMedecin; }

    public int getNbJours() { return nbJours; }
    public void setNbJours(int nbJours) { this.nbJours = nbJours; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public java.sql.Date getDaty() { return daty;}
    public void setDaty(java.sql.Date daty) { this.daty = daty;}
    
    // Nouveaux Getters et Setters
    public String getIdTypeArret() { return idTypeArret; }
    public void setIdTypeArret(String idTypeArret) { this.idTypeArret = idTypeArret; }
    
    public java.sql.Date getDateDebut() { return dateDebut; }
    public void setDateDebut(java.sql.Date dateDebut) { this.dateDebut = dateDebut; }
    
    public java.sql.Date getDateFin() { return dateFin; }
    public void setDateFin(java.sql.Date dateFin) { this.dateFin = dateFin; }
    
    public String getIdTypeSoins() { return idTypeSoins; }
    public void setIdTypeSoins(String idTypeSoins) { this.idTypeSoins = idTypeSoins; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public int getEtat() { return etat; }
    public void setEtat(int etat) { this.etat = etat; }
    
    public String getObservationSoins() { return observationSoins; }
    public void setObservationSoins(String observationSoins) { this.observationSoins = observationSoins; }
    
    public String getIdentite() { return identite; }
    public void setIdentite(String identite) { this.identite = identite; }
    
    public String getIdRetraite() { return idRetraite; }
    public void setIdRetraite(String idRetraite) { this.idRetraite = idRetraite; }
    
    public String getIdDeces() { return idDeces; }
    public void setIdDeces(String idDeces) { this.idDeces = idDeces; }
    
    public String getIdMembre() { return idMembre; }
    public void setIdMembre(String idMembre) { this.idMembre = idMembre; }
    
    public String getSocietePriseEncharge() { return societePriseEncharge; }
    public void setSocietePriseEncharge(String societePriseEncharge) { this.societePriseEncharge = societePriseEncharge; }
}