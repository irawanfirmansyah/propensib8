package com.project.propensib8.service;

import com.project.propensib8.model.ReviewModel;

import java.util.List;

public interface ReviewService {
    int countReviewByNama(String nama);
    List<ReviewModel> getReviewByNama(String nama);
    List<String> getNamaPasienReviewByNama(String nama);
    List<String> getDeskripsiReviewByNama(String nama);
}
