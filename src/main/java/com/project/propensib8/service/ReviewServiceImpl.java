package com.project.propensib8.service;

import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.repository.ReviewDB;
import com.project.propensib8.rest.ReviewRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewDB reviewDb;

	@Override
	public int countReviewByNama(String nama) {
		int count = 0;
		for(int num = 0; num < reviewDb.findAll().size(); num++){
			if(reviewDb.findAll().get(num).getUnit().getNama().equalsIgnoreCase(nama)){
				count += 1;
			}
		}
		return count;
	}

	@Override
	public List<ReviewModel> getReviewByNama(String nama) {
		List<ReviewModel> list = new ArrayList<>();
		for(ReviewModel review: reviewDb.findAll()){
			if(review.getUnit().getNama().equalsIgnoreCase(nama)){
				list.add(review);
			}
		}
		return list;
	}

	@Override
	public List<String> getNamaPasienReviewByNama(String nama) {
		List<String> list = new ArrayList<>();
		List<ReviewModel> listOfReview = reviewDb.findAll();
		for(ReviewModel review: listOfReview) {
			if (review.getUnit().getNama().equalsIgnoreCase(nama)){
				list.add(review.getSurvei().getPasien().getNama());
			}
		}
		return list;
	}

	@Override
	public List<String> getDeskripsiReviewByNama(String nama) {
		List<String> list = new ArrayList<>();
		List<ReviewModel> listOfReview = reviewDb.findAll();
		for(ReviewModel review: listOfReview){
			if(review.getUnit().getNama().equalsIgnoreCase(nama)){
				list.add(review.getDeskripsi());
			}
		}
		return list;
	}

	@Override
	public List<ReviewRest> createReviewRest(String namaUnit) {
		List<ReviewRest> list = new ArrayList<>();
		for(ReviewModel review: reviewDb.findAll()){
			if(review.getUnit().getNama().equalsIgnoreCase(namaUnit)) {
				ReviewRest reviewRest = new ReviewRest();
				reviewRest.setNama(review.getSurvei().getPasien().getNama());
				reviewRest.setDeskripsi(review.getDeskripsi());
				reviewRest.setRating(review.getSurvei().getRating());
				java.sql.Date sqlDate = review.getSurvei().getTanggal();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String resDate = formatter.format(sqlDate);
				reviewRest.setTanggalIsi(resDate);
				list.add(reviewRest);
			}
		}
		return list;
	}
}