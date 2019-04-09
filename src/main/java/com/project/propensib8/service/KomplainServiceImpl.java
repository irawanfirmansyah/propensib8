package com.project.propensib8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.ParameterModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.ParameterDB;

@Service
@Transactional
public class KomplainServiceImpl implements KomplainService{

    @Autowired
    KomplainDB komplainDb;

    @Autowired
    PasienService pasienService;

	@Override
	public PasienModel getPasienByNamaTanggal(String namaPasien, String tanggalPengisian) {
        PasienModel picked = new PasienModel();

        for (KomplainModel komplain: komplainDb.findAll()){
            if(komplain.getSurvei().getPasien().getNama().equals(namaPasien)){
                if(komplain.getSurvei().getTanggal().equals(tanggalPengisian)){
                    picked = komplain.getSurvei().getPasien();
                }
            }
        }
		return picked;
	}
	
    
}
