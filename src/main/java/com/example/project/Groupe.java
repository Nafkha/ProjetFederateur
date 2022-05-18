package com.example.project;

public class Groupe {
    private String idGrp,niveau,diplome,specialite;
    private int num_grp;

    public Groupe(String idGrp, String niveau, String diplome, String specialite, int num_grp) {
        this.idGrp = idGrp;
        this.niveau = niveau;
        this.diplome = diplome;
        this.specialite = specialite;
        this.num_grp = num_grp;
    }

    public String getIdGrp() {
        return idGrp;
    }

    public void setIdGrp(String idGrp) {
        this.idGrp = idGrp;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNum_grp() {
        return num_grp;
    }

    public void setNum_grp(int num_grp) {
        this.num_grp = num_grp;
    }
}
