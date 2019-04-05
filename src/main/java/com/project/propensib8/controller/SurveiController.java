package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;

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
import com.project.propensib8.service.SurveiService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/survei")
public class SurveiController {
	@Autowired
	private SurveiService surveiService;
	
	@Autowired
	private SurveiDB surveiDb;
	
	@PostMapping(value="/add")
	public ResponseEntity<?> createSurvei(@Valid @RequestBody SurveiModel survei) throws URISyntaxException{
		System.out.println(survei.getRating());
		System.out.println(survei.getRating());
		SurveiModel result = surveiDb.save(survei);
		return ResponseEntity.created(new URI("/survei/add/" + result.getId())).body(result);
	}
	//CRUD controller goes here
}
