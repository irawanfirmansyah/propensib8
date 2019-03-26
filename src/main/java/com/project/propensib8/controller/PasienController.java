package com.project.propensib8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.PasienModel;
import com.project.propensib8.service.PasienService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pasien")
public class PasienController {
	@Autowired
	private PasienService pasienService;
	
	@GetMapping(value = "/{idMedrec}")
	public ResponseEntity<?> getPasien(@PathVariable("idMedrec") String idMedrec) {
		PasienModel result = pasienService.getPasienByIdMedrec(idMedrec);
    	return new ResponseEntity(result, HttpStatus.OK);
	}
	
}
