package com.example.project;

public class Absence {
    private String mat,h,t;
    private int etu;

    public Absence(String mat, String h, String t, int etu) {
        this.mat = mat;
        this.h = h;
        this.t = t;
        this.etu = etu;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int getEtu() {
        return etu;
    }

    public void setEtu(int etu) {
        this.etu = etu;
    }
}
