package com.project.propensib8.rest;

import com.project.propensib8.model.UnitParameterModel;
import java.util.List;

public class DetailPerforma {
    int rating;
    int komplain;
    List<UnitParameterModel> listOfUnitParameter;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getKomplain() {
        return komplain;
    }

    public void setKomplain(int komplain) {
        this.komplain = komplain;
    }

    public List<UnitParameterModel> getListOfUnitParameter() {
        return listOfUnitParameter;
    }

    public void setListOfUnitParameter(List<UnitParameterModel> listOfUnitParameter) {
        this.listOfUnitParameter = listOfUnitParameter;
    }

}
