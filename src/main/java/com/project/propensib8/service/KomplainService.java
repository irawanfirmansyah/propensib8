package com.project.propensib8.service;

<<<<<<< HEAD
import java.util.List;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;

public interface KomplainService {
	
    PasienModel getPasienByNamaTanggal(String namaPasien, String tanggalPengisian);
    KomplainModel getKomplainByNamaPasien(String namaPasien);
    List<KomplainModel> getKomplainByNamaTanggal(String namaPasien, String tanggalPengisian);
}
=======
import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.SurveiModel;

import java.util.List;

public interface KomplainService {
	
    KomplainModel getKomplainById(long id);
    List<KomplainModel> findAll();
}
>>>>>>> aca69f684d7b8cc926d9d505a70fd0b96369208a
