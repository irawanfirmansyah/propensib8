package com.project.propensib8.rest;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.model.UnitParameterModel;
import java.util.List;

public class DetailPerforma {
    int review;
    int komplain;
    RestKomplainReview res;

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getKomplain() {
        return komplain;
    }

    public void setKomplain(int komplain) {
        this.komplain = komplain;
    }

    public RestKomplainReview getRes() {
        return res;
    }

    public void setRes(RestKomplainReview res) {
        this.res = res;
    }

}
