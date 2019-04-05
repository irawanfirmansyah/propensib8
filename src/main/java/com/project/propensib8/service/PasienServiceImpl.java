package com.project.propensib8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.PasienDB;

@Service
@Transactional
public class PasienServiceImpl implements PasienService{

	@Autowired
	PasienDB pasienDb;

	@Override
	public PasienModel getPasienByIdMedrec(String idMedrec) {
		return pasienDb.findByIdMedrec(idMedrec);
	}

	
}
