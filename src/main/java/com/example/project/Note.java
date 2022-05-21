package com.example.project;

public class Note {
      private  String mat;
       private int etu;
       private double note;
       private String type;

    public Note(String mat, int etu, double note, String type) {
        this.mat = mat;
        this.etu = etu;
        this.note = note;
        this.type = type;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public int getEtu() {
        return etu;
    }

    public void setEtu(int etu) {
        this.etu = etu;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
