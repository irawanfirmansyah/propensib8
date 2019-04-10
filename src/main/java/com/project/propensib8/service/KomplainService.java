package com.project.propensib8.service;

import java.util.List;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;

public interface KomplainService {
	
    PasienModel getPasienByNamaTanggal(String namaPasien, String tanggalPengisian);
    // boolean tanggalKomplainSama(KomplainModel );
    KomplainModel getKomplainByNamaPasien(String namaPasien);
    List<KomplainModel> getKomplainByNamaTanggal(String namaPasien, String tanggalPengisian);
}