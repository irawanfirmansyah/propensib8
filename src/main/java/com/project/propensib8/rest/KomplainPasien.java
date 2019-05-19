package com.project.propensib8.rest;

import com.project.propensib8.model.PasienModel;

import java.util.List;

import com.project.propensib8.model.KomplainModel;;

public class KomplainPasien {
    private PasienModel pasien;
    private int rating;
    private String idSurvei;
    private String namaUnit;
    private List<KomplainResponse> listKomplain;

    
    /**
     * @return the pasien
     */
    public PasienModel getPasien() {
        return pasien;
    }

    /**
     * @param pasien the pasien to set
     */
    public void setPasien(PasienModel pasien) {
        this.pasien = pasien;
    }

    /**
     * @return the listKomplain
     */
    public List<KomplainResponse> getListKomplain() {
        return listKomplain;
    }

    /**
     * @param listKomplain the listKomplain to set
     */
    public void setListKomplain(List<KomplainResponse> listKomplain) {
        this.listKomplain = listKomplain;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getIdSurvei() {
        return idSurvei;
    }

    public void setIdSurvei(String idSurvei) {
        this.idSurvei = idSurvei;
    }

    public String getNamaUnit() {
        return namaUnit;
    }

    public void setNamaUnit(String namaUnit) {
        this.namaUnit = namaUnit;
    }
}