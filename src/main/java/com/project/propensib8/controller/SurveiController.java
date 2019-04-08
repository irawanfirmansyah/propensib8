package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.repository.SurveiDB;
import com.project.propensib8.service.PasienService;
import com.project.propensib8.service.SurveiService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/survei")
public class SurveiController {
	@Autowired
	private SurveiService surveiService;
	
	@Autowired
	private PasienService pasienService;
	
	@Autowired
	private SurveiDB surveiDb;
	
	@PostMapping(value="/add")
	public ResponseEntity<?> createSurvei(@Valid @RequestBody Map<String, String> res) throws URISyntaxException, ParseException{
		SurveiModel result = new SurveiModel();
		
		System.out.println(res.get("idPasien"));
		
		result.setRating(Integer.parseInt(res.get("rating")));
		result.setJenisSurvei(res.get("jenisSurvei"));
		result.setPasien(pasienService.getPasienById(res.get("idPasien")));
		
		System.out.println(res.get("tanggal"));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date parsed = format.parse(res.get("tanggal"));
		java.sql.Date dateSql = new java.sql.Date(parsed.getTime());
		result.setTanggal(dateSql);
		result.setFeedback(res.get("feedback"));
		
		result = surveiDb.save(result);
		return ResponseEntity.created(new URI("/survei/add/" + result.getId())).body(result);
	}
	//CRUD controller goes here
}
