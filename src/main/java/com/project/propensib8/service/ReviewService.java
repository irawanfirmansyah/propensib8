package com.project.propensib8.service;

import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.rest.ReviewRest;

import java.sql.Date;
import java.util.List;

public interface ReviewService {
    int countReviewByNama(String nama);
    List<ReviewModel> getReviewByNama(String nama);
    List<String> getNamaPasienReviewByNama(String nama);
    List<String> getDeskripsiReviewByNama(String nama);
    List<ReviewRest> createReviewRest(String namaUnit);
    String getMostCommonUnit(Date startDate, Date endDate, String tipeSurvei);
    List<ReviewModel> findAllByTanggal(Date startDate, Date endDate);
    List<String> isiReview(Date startDate, Date endDate, String tipeSurvei);
}
