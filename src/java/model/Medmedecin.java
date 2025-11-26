package model;

public class Medmedecin {
    private String id;
    private String nom;
    private String prenom;
    private String matricule;
    private String telephone;
    private String email;
    private String niveau;
    private double tarifHoraire;
    private String centre;
    private int niveauRole;
    private int idUser;
    private double pourcentage;
    private String profile;

    // Constructeur par défaut
    public Medmedecin() {
    }

    // Constructeur avec tous les paramètres
    public Medmedecin(String id, String nom, String prenom, String matricule, 
                     String telephone, String email, String niveau, double tarifHoraire, 
                     String centre, int niveauRole, int idUser, double pourcentage, String profile) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.telephone = telephone;
        this.email = email;
        this.niveau = niveau;
        this.tarifHoraire = tarifHoraire;
        this.centre = centre;
        this.niveauRole = niveauRole;
        this.idUser = idUser;
        this.pourcentage = pourcentage;
        this.profile = profile;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public double getTarifHoraire() {
        return tarifHoraire;
    }

    public void setTarifHoraire(double tarifHoraire) {
        this.tarifHoraire = tarifHoraire;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public int getNiveauRole() {
        return niveauRole;
    }

    public void setNiveauRole(int niveauRole) {
        this.niveauRole = niveauRole;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "MedMedecin{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", niveau='" + niveau + '\'' +
                ", tarifHoraire=" + tarifHoraire +
                ", centre='" + centre + '\'' +
                ", niveauRole=" + niveauRole +
                ", idUser=" + idUser +
                ", pourcentage=" + pourcentage +
                ", profile='" + profile + '\'' +
                '}';
    }
}