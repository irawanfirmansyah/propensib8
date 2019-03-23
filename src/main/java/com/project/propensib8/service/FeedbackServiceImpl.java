package com.project.propensib8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.FeedbackModel;
import com.project.propensib8.repository.FeedbackDB;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{
	@Autowired
	FeedbackDB feedbackDb;
	
	//your code goes here ...
}
