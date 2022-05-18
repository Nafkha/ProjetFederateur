package com.example.project;

public class Matiere {
    private  String idMat,nomMat,gm;
    private double coef;
    private int nbnotes,ens;

    public Matiere(String idMat, String nomMat, String gm, double coef, int nbnotes, int ens) {
        this.idMat = idMat;
        this.nomMat = nomMat;
        this.gm = gm;
        this.coef = coef;
        this.nbnotes = nbnotes;
        this.ens = ens;
    }

    public String getIdMat() {
        return idMat;
    }

    public void setIdMat(String idMat) {
        this.idMat = idMat;
    }

    public String getNomMat() {
        return nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    public String getGm() {
        return gm;
    }

    public void setGm(String gm) {
        this.gm = gm;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public int getNbnotes() {
        return nbnotes;
    }

    public void setNbnotes(int nbnotes) {
        this.nbnotes = nbnotes;
    }

    public int getEns() {
        return ens;
    }

    public void setEns(int ens) {
        this.ens = ens;
    }
}
