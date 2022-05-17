package com.example.project;

public class Etudiant extends Personne
{
    private int num_insc;
    private String grp;
    private int num_grp;

    public Etudiant(int cin, String nom, String prenom, String sexe, String date_naissance, String mail, int num_insc,String grp, int num_grp)
    {
        super(cin, nom, prenom, sexe, date_naissance, mail);
        this.num_insc = num_insc;
        this.grp = grp;
        this.num_grp = num_grp;
    }


    public int getNum_insc()
    {
        return num_insc;
    }

    public void setNum_insc(int num_insc)
    {
        this.num_insc = num_insc;
    }

    public String getGrp() {
        return grp;
    }

    public int getNum_grp() {
        return num_grp;
    }
}
