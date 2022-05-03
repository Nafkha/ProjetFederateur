package com.example.project;

public class Etudiant extends Personne
{
    private int num_insc;

    public Etudiant(int cin, String nom, String prenom, String sexe, int age, String mail, int num_insc)
    {
        super(cin, nom, prenom, sexe, age, mail);
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
