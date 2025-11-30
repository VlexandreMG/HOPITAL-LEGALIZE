package model;

public class MedOrdonnance {
    private String id;
    private String idConsultation;
    private String idMedecin;
    private int nbJours;
    private String observation;
    private java.sql.Date daty;


    public MedOrdonnance() {}

    public MedOrdonnance(String id, String idConsultation, String idMedecin, int nbJours, String observation, java.sql.Date daty) {
        this.id = id;
        this.idConsultation = idConsultation;
        this.idMedecin = idMedecin;
        this.nbJours = nbJours;
        this.observation = observation;
        this.daty = daty;
    }

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
}