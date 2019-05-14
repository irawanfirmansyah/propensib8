package com.project.propensib8.service;

import java.util.List;

import com.project.propensib8.model.SurveiModel;

public interface SurveiService {

	List<SurveiModel> getAllKomplain();
	SurveiModel getSurveiById(String id);
}
