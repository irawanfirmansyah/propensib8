package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.repository.KomplainDB;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.service.KomplainService;
import com.project.propensib8.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/komplain")
public class KomplainController {	
	@Autowired
	private KomplainDB komplainDb;
	
	@Autowired
	private KomplainService komplainService;

	@GetMapping(value = "/{namaKomplain}")
	public ResponseEntity<?> getKomplainByName(@PathVariable("namaKomplain") String namaKomplain){
		List<KomplainModel> listKomplain = komplainDb.findAll();
		return new ResponseEntity(listKomplain,HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public KomplainModel getPasienById(@PathVariable("id") long id) {
		return komplainService.getKomplainById(id);
	}
}
