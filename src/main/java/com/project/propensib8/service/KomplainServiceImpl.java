package com.project.propensib8.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;

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

    @Override
    public KomplainModel getKomplainByNamaPasien(String namaPasien) {
	    KomplainModel picked = null;
	    for(KomplainModel komplain: komplainDb.findAll()) {
            if (komplain.getSurvei().getPasien().getNama().equalsIgnoreCase(namaPasien)) {
                picked = komplain;
            }
        }
        return picked;
    }

    @Override
    public List<KomplainModel> getKomplainByNamaTanggal(String namaPasien, String tanggalPengisian) {
        String tanggalPicked = "";
        List<KomplainModel> listOfKomplain = new ArrayList<>();
        for(KomplainModel komplain: komplainDb.findAll()){

            java.sql.Date sqlDate = komplain.getSurvei().getTanggal();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            tanggalPicked = formatter.format(sqlDate);

            if(komplain.getSurvei().getPasien().getNama().equalsIgnoreCase(namaPasien) && tanggalPicked.equalsIgnoreCase(tanggalPengisian)){
                listOfKomplain.add(komplain);
            }
        }
        return listOfKomplain;
    }
    
	@Override
	public KomplainModel getKomplainById(long id) {
		return komplainDb.findById(id);
	}

	@Override
	public List<KomplainModel> findAll() {
		List<KomplainModel> listOfKomplain = new ArrayList<>();
		for(KomplainModel komplain : komplainDb.findAll()){
		    if(komplain.getSurvei().getRating() <= 3 && komplain.isSolved() == false){
		        listOfKomplain.add(komplain);
            }
        }
	    return listOfKomplain;
	}

    @Override
    public List<KomplainModel> findAllSolvedKomplain() {
	    List<KomplainModel> listOfKomplain = new ArrayList<>();
	    for(KomplainModel komplain : komplainDb.findAll()){
	        if(komplain.isSolved() == true){
                listOfKomplain.add(komplain);
            }
        }
        return listOfKomplain;
    }

    public KomplainModel createKomplain(KomplainModel komplainModel) {
		return komplainDb.save(komplainModel);
	}

}
