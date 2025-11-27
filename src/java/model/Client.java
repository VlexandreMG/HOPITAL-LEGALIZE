package model;

public class Client {
    String id;
    String nom;

    public Client() {
    }

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

    @Override
    public String toString() {
        return "Client [getId()=" + getId() + ", getNom()=" + getNom() + "]";
    }

}
