package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.repository.UnitDB;
import com.project.propensib8.rest.DetailPerforma;
import com.project.propensib8.rest.PerformaKaryawan;
import com.project.propensib8.rest.RestKomplainReview;
import com.project.propensib8.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.ParameterModel;
import com.project.propensib8.model.UnitModel;
import com.project.propensib8.model.UnitParameterModel;
import com.project.propensib8.repository.UnitParameterDB;
import com.project.propensib8.rest.BaseResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/unit-parameter")
public class UnitParameterController {	
	@Autowired
	private UnitParameterDB unitParameterDb;
	@Autowired
	private UnitService unitService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private UnitDB unitDb;
	@Autowired
	private KomplainDB komplainDb;
	@Autowired
	private KomplainService komplainService;
	@Autowired
	private ReviewService reviewService;


	@PostMapping(value="/add")
	public ResponseEntity<?> createUnitParameter(@Valid @RequestBody Map<String, String> res) throws URISyntaxException, ParseException{
		
		UnitParameterModel result = new UnitParameterModel();
		UnitModel unitModel = unitService.getUnitByName(res.get("unit"));
		ParameterModel parameterModel = parameterService.getParameterByName(res.get("parameter"));
		
		System.out.println(unitModel.getNama());
		System.out.println(parameterModel.getNama());
		
		result.setParameter(parameterModel);
		result.setUnit(unitModel);
		result = unitParameterDb.save(result);
		BaseResponse<UnitParameterModel> response = new BaseResponse<>();
		
		if(result.getId() != 0) {
			response.setStatus(200);
			response.setMessage("success");
			response.setResult(result);
		}
		
//		return ResponseEntity.created(new URI("/survei/add/" + result.getId())).body(result);
		return ResponseEntity.created(new URI("/survei")).body(response);
	}

	@GetMapping(value = "/performa/{namaUnit}")
	public ResponseEntity<?> getAllPerforma(@PathVariable ("namaUnit") String namaUnit) {
		PerformaKaryawan performa = new PerformaKaryawan();
		performa.setNamaUnit(namaUnit);
		performa.setKomplain(komplainService.countKomplainByNama(namaUnit));
		performa.setIdUnit(Long.toString(unitService.getUnitByName(namaUnit).getId()));
		performa.setReview(reviewService.countReviewByNama(namaUnit));
		return new  ResponseEntity(performa, HttpStatus.OK);
	}

	@GetMapping(value = "/detail-performa/{namaUnit}")
	public ResponseEntity<?> getDetailPerforma(@PathVariable ("namaUnit") String namaUnit){
		DetailPerforma detailPerforma = new DetailPerforma();
		RestKomplainReview res = new RestKomplainReview();
		res.setListReview(reviewService.getNamaPasienReviewByNama(namaUnit));
		res.setListKomplain(komplainService.getNamaPasienKomplainByNama(namaUnit));
		detailPerforma.setKomplain(komplainService.countKomplainByNama(namaUnit));
		detailPerforma.setReview(reviewService.countReviewByNama(namaUnit));
		detailPerforma.setRes(res);
		return new ResponseEntity<>(detailPerforma, HttpStatus.OK);
	}

}
