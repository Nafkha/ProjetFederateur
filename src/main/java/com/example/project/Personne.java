package com.example.project;

public class Personne
{
    private int cin;
    private String nom;
    private String prenom;
    private String sexe;
    private String date_naissence;
    private String mail;

    public Personne(int cin, String nom, String prenom, String sexe, String date_naissence, String mail)
    {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date_naissence = date_naissence;
        this.mail = mail;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getDate_naissence() {
        return date_naissence;
    }

    public String getMail() {
        return mail;
    }
}
