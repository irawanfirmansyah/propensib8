package com.project.propensib8.service;

import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.repository.ReviewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		int counter = 0;
		if (reviewDb.findAll().size() <= 3 && reviewDb.findAll().size() >0) {
			for (int i=0 ; i<reviewDb.findAll().size() ; i++) {
				if (listOfReview.get(i).getUnit().getNama().equalsIgnoreCase(nama)) {
					list.add(listOfReview.get(i).getSurvei().getPasien().getNama()+","+listOfReview.get(i).getDeskripsi());
				}
			}
		} else {
			for (int i=0 ; i<reviewDb.findAll().size() ; i++) {
				if(counter != 3) {
					if (listOfReview.get(i).getUnit().getNama().equalsIgnoreCase(nama)){
						list.add(listOfReview.get(i).getSurvei().getPasien().getNama()+","+listOfReview.get(i).getDeskripsi());
						counter ++;
					}
				}
				else {
					break;
				}
			}
		}
		return list;
	}

	@Override
	public List<String> getDeskripsiReviewByNama(String nama) {
		List<String> list = new ArrayList<>();
		List<ReviewModel> listOfReview = reviewDb.findAll();
		if (reviewDb.findAll().size() <= 3) {
			for (ReviewModel review : reviewDb.findAll()) {
				if (review.getUnit().getNama().equalsIgnoreCase(nama)) {
					list.add(review.getDeskripsi());
				}
			}
		} else {
			int counter = 1;
			for (ReviewModel review : reviewDb.findAll()) {
				if (review.getUnit().getNama().equalsIgnoreCase(nama) && counter <= 3) {
					list.add(review.getDeskripsi());
					counter += 1;
				}
			}
		}
		return list;
	}


}
