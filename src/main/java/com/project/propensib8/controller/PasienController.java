package com.project.propensib8.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.PasienDB;
import com.project.propensib8.rest.KomplainDetail;
import com.project.propensib8.service.PasienService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pasien")
public class PasienController {
	@Autowired
	private PasienService pasienService;

	@Autowired
	private KomplainDB komplainDb;

	@Autowired
	private PasienDB pasienDb;

	@GetMapping(value = "/{idMedrec}")
	public ResponseEntity<?> getPasien(@PathVariable("idMedrec") String idMedrec) {
		PasienModel result = pasienService.getPasienByIdMedrec(idMedrec);
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@GetMapping(value = "/overview-complaint")
	public ResponseEntity<?> getOverviewComplaint(){
		
		List<KomplainDetail> result =  new ArrayList<>();

		List<KomplainModel> listKomplain = komplainDb.findAll();

		listKomplain.get(0).getUnit().getSurvei().getRating();
		for(KomplainModel k : listKomplain){
			KomplainDetail details = new KomplainDetail();
			details.setNamaPasien(k.getUnit().getSurvei().getPasien().getNama());
			details.setRating(k.getUnit().getSurvei().getRating());
			details.setId(String.valueOf(k.getUnit().getSurvei().getPasien().getId()));
			
			System.out.println(k.getUnit().getSurvei().getTanggal());
			
			java.sql.Date sqlDate = k.getUnit().getSurvei().getTanggal();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String resDate = formatter.format(sqlDate);

			System.out.println(resDate);
			details.setTanggalPengisian(resDate);
			result.add(details);
		}
		
		return new ResponseEntity(result, HttpStatus.OK);
	}

}
