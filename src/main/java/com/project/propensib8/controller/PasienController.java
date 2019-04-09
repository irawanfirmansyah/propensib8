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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.PasienModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.PasienDB;
import com.project.propensib8.rest.KomplainDetail;
import com.project.propensib8.rest.KomplainPasienDetail;
import com.project.propensib8.service.KomplainService;
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

	@Autowired
	private KomplainService komplainService;

	@GetMapping(value = "/{idMedrec}")
	public ResponseEntity<?> getPasien(@PathVariable("idMedrec") String idMedrec) {
		PasienModel result = pasienService.getPasienByIdMedrec(idMedrec);
		return new ResponseEntity(result, HttpStatus.OK);
	}

	//	@GetMapping(value = "/overview-complaint")
	//	public ResponseEntity<?> getOverviewComplaint(){
	//		
	//		List<KomplainDetail> result =  new ArrayList<>();
	//
	//		List<KomplainModel> listKomplain = komplainDb.findAll();
	//
	//		listKomplain.get(0).getUnit().getSurvei().getRating();
	//		for(KomplainModel k : listKomplain){
	//			KomplainDetail details = new KomplainDetail();
	//			details.setNamaPasien(k.getUnit().getSurvei().getPasien().getNama());
	//			details.setRating(k.getUnit().getSurvei().getRating());
	//			details.setId(String.valueOf(k.getId()));
	//
	//			java.sql.Date sqlDate = k.getUnit().getSurvei().getTanggal();
	//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	//			String resDate = formatter.format(sqlDate);
	//			
	//			details.setTanggalPengisian(resDate);
	//			result.add(details);
	//		}
	//		
	//		return new ResponseEntity(result, HttpStatus.OK);
	//	}


	//	@GetMapping(value = "/{idKomplain}")
	//	public ResponseEntity<?> getKomplainById(@PathVariable ("idKomplain") String idKomplain){
	//		
	//		KomplainPasienDetail komplain = new KomplainPasienDetail();
	//		komplain.setAlamat();
	//		return new ResponseEntity(listPasien, HttpStatus.OK);
	//	}

	@GetMapping(value = "/komplain")
	public ResponseEntity<?> getKomplainPasien(){
		List<KomplainModel> allKomplain = komplainDb.findAll();
		List<KomplainPasienDetail> res = new ArrayList<>();

		for(KomplainModel k : allKomplain) {
			System.out.println("komplain");
			KomplainPasienDetail details = new KomplainPasienDetail();
			String lastPasien = "";

			if(res.size() == 0) {
				details.setNamaPasien(k.getSurvei().getPasien().getNama());

				details.setRating(k.getSurvei().getRating());

				java.sql.Date sqlDate = k.getSurvei().getTanggal();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String resDate = formatter.format(sqlDate);

				details.setTanggalKomplain(resDate);
				List<KomplainModel> listKomplain = new ArrayList<>();
				listKomplain.add(k);
				details.setListKomplain(listKomplain);
				res.add(details);
			}

			else {
				lastPasien = res.get(res.size()-1).getNamaPasien();
				if(lastPasien.equalsIgnoreCase(k.getSurvei().getPasien().getNama())) {
					//masukkan objek komplain miliki pasien ke KomplainPasienDetail yang sudah ada di array list
					res.get(res.size()-1).getListKomplain().add(k);
				}
				else {
					details.setNamaPasien(k.getSurvei().getPasien().getNama());

					details.setRating(k.getSurvei().getRating());

					java.sql.Date sqlDate = k.getSurvei().getTanggal();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String resDate = formatter.format(sqlDate);

					details.setTanggalKomplain(resDate);
					List<KomplainModel> listKomplain = new ArrayList<>();
					listKomplain.add(k);
					details.setListKomplain(listKomplain);
					res.add(details);
				}
			}
		}

		return new ResponseEntity(res, HttpStatus.OK);
	}

	@GetMapping(value = "/komplain/{namaPasien}/{tanggalPengisian}")
	public ResponseEntity<?> getDetailKomplain(@PathVariable ("namaPasien") String namaPasien, @PathVariable ("tanggalPengisian") String tanggalPengisian){
		PasienModel pasien = komplainService.getPasienByNamaTanggal(namaPasien, tanggalPengisian);
		
	}

}
