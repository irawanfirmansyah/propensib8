package com.project.propensib8.service;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.repository.KomplainDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class KomplainServiceImpl implements KomplainService{

	@Autowired
	KomplainDB komplainDb;

	@Override
	public KomplainModel getKomplainById(long id) {
		return komplainDb.findById(id);
	}

	@Override
	public List<KomplainModel> findAll() {
		return komplainDb.findAll();
	}

	public KomplainModel createKomplain(KomplainModel komplainModel) {
		return komplainDb.save(komplainModel);
	}

}
