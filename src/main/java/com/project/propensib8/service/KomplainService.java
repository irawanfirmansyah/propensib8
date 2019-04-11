package com.project.propensib8.service;

import java.util.List;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;

public interface KomplainService {
	
    PasienModel getPasienByNamaTanggal(String namaPasien, String tanggalPengisian);
    KomplainModel getKomplainByNamaPasien(String namaPasien);
    List<KomplainModel> getKomplainByNamaTanggal(String namaPasien, String tanggalPengisian);	
    KomplainModel getKomplainById(long id);
    List<KomplainModel> findAll();
    List<KomplainModel> findAllSolvedKomplain();
    List<KomplainModel> getKomplainByNama(String nama);
    int countKomplainByNama(String nama);
    int countRatingByNama(String nama);
}
