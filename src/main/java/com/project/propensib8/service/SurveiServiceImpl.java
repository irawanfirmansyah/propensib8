package com.project.propensib8.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.repository.SurveiDB;

@Service
@Transactional
public class SurveiServiceImpl implements SurveiService{
	@Autowired
	SurveiDB surveiDb;

	@Override
	public List<SurveiModel> getAllKomplain() {
		List<SurveiModel> res = new ArrayList<>();
		List<SurveiModel> listSurvei = surveiDb.findAll();
		for(SurveiModel s : listSurvei){
			if(s.getRating() <= 3){
				res.add(s);
			}
		}

		return res;
	}

	@Override
	public SurveiModel getSurveiById(String id) {
		return surveiDb.findById(Long.parseLong(id));
	}


}