package com.example.project;

public class Enseignant extends Personne {
    private int cnss;

    public Enseignant(int cin, String nom, String prenom, String sexe, int age, String mail, int cnss) {
        super(cin, nom, prenom, sexe, age, mail);
        this.cnss = cnss;
    }

    public int getCnss() {
        return cnss;
    }

    public void setCnss(int cnss) {
        this.cnss = cnss;
    }
}
