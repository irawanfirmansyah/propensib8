package com.project.propensib8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.UnitModel;
import com.project.propensib8.repository.UnitDB;

@Service
@Transactional
public class UnitServiceImpl implements UnitService{
	@Autowired
	UnitDB unitDb;
	
	//your code goes here...
}
