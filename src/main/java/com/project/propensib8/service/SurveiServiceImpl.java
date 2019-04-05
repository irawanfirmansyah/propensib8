package com.project.propensib8.service;

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

	//your code goes here ...
}