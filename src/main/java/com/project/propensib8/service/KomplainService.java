package com.project.propensib8.service;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.SurveiModel;

import java.util.List;

public interface KomplainService {
	
    KomplainModel getSurveiById(long id);
    List<KomplainModel> findAll();
}
