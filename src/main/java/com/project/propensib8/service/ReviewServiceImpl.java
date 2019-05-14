package com.project.propensib8.service;

import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.repository.ReviewDB;
import com.project.propensib8.rest.ReviewRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

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

	@Override
	public String getMostCommonUnit(Date startDate, Date endDate, String tipeSurvei) {
		List<ReviewModel> list = this.findAllByTanggal(startDate, endDate);
		List<ReviewModel> filterTipeSurvei = new ArrayList<>();

		for(ReviewModel review : list){
			if(review.getSurvei().getJenisSurvei().equalsIgnoreCase(tipeSurvei)){
				filterTipeSurvei.add(review);
			}
			else {
				filterTipeSurvei.add(review);
			}
		}
		Map<String, Integer> map = new HashMap<>();
		for(ReviewModel review : filterTipeSurvei){
			Integer val = map.get(review.getUnit().getNama());
			map.put(review.getUnit().getNama(), val == null ? 1 : val + 1);
		}

		Map.Entry<String, Integer> max = null;

		for(Map.Entry<String, Integer> unit : map.entrySet()){
			if(max == null || unit.getValue() > max.getValue()){
				max = unit;
			}
		}
		return max.getKey();
	}

	@Override
	public List<ReviewModel> findAllByTanggal(Date startDate, Date endDate) {
		if(startDate == null || endDate == null){
			return reviewDb.findAll();
		}
		return reviewDb.findAllByTanggalBetween(startDate, endDate);
	}

	@Override
	public List<String> isiReview(Date startDate, Date endDate, String tipeSurvei) {
		List<ReviewModel> list = this.findAllByTanggal(startDate,endDate);
		List<String> listOfReview = new ArrayList<>();
		for(ReviewModel review : list){
			if (review.getSurvei().getJenisSurvei().equalsIgnoreCase(tipeSurvei)) {
				listOfReview.add(review.getDeskripsi());
			}
			else {
				listOfReview.add(review.getDeskripsi());
			}
			if(listOfReview.size() == 5){
				break;
			}
		}
		return listOfReview;
	}
}
