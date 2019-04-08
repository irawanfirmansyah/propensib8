package com.project.propensib8.rest;

public class KomplainDetail {
    String idKomplain;
    String namaPasien;
    String tanggalPengisian;
    int rating;

    public String getTanggalPengisian() {
        return tanggalPengisian;
    }

    public void setTanggalPengisian(String tanggal) {
        this.tanggalPengisian = tanggal;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getId() {
        return idKomplain;
    }

    public void setId(String id) {
        this.idKomplain = id;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }
}