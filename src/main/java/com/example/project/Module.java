package com.example.project;

public class Module {
    private String idgm,nom,grp;
    private double coef;

    public Module(String idgm, String nom, String grp, double coef) {
        this.idgm = idgm;
        this.nom = nom;
        this.grp = grp;
        this.coef = coef;
    }

    public String getIdgm() {
        return idgm;
    }

    public void setIdgm(String idgm) {
        this.idgm = idgm;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }
}
