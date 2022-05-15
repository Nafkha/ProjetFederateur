package com.example.project;

public class Etudiant extends Personne
{
    private int num_insc;

    public Etudiant(int cin, String nom, String prenom, String sexe, String date_naissance, String mail, int num_insc)
    {
        super(cin, nom, prenom, sexe, date_naissance, mail);
        this.num_insc = num_insc;
    }


    public int getNum_insc()
    {
        return num_insc;
    }

    public void setNum_insc(int num_insc)
    {
        this.num_insc = num_insc;
    }
}
