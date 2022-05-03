package com.example.project;

public class Personne
{
    private int cin;
    private String nom;
    private String prenom;
    private String sexe;
    private int age;
    private String mail;

    public Personne(int cin, String nom, String prenom, String sexe, int age, String mail)
    {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.mail = mail;
    }

    public int getCin()
    {
        return cin;
    }

    public void setCin(int cin)
    {
        this.cin = cin;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getSexe()
    {
        return sexe;
    }

    public void setSexe(String sexe)
    {
        this.sexe = sexe;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }
}
