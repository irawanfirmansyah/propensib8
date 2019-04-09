package com.project.propensib8.service;

import java.util.List;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;

public interface KomplainService {
	
    PasienModel getPasienByNamaTanggal(String namaPasien, String tanggalPengisian);
}
