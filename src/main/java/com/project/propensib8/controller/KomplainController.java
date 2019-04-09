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

	@GetMapping(value="/")
	public List<KomplainModel> getAllKomplain() {
		return komplainService.findAll();
	}

	@GetMapping(value = "/create")
	public KomplainModel createKomplain(@RequestBody Map<String, String> body)  {
		List<Integer> tgl = Arrays.stream(body.get("tanggal").split(","))
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		String komplain = body.get("komplain");
		int idPasien = Integer.parseInt(body.get("idPasien")) ;
		Calendar tanggal = new GregorianCalendar(tgl.get(0), tgl.get(1), tgl.get(2));
		int jenisKomplain = Integer.parseInt(body.get("jenisKomplain"));
		int rating = Integer.parseInt(body.get("rating"));
		int urgensi = Integer.parseInt(body.get("urgensi"));
		KomplainModel komplain = new KomplainModel();

		return komplainDb.save(new KomplainModel(komplain, tanggal, jenisKomplain, rating, idPasien, urgensi));
	}

	@RequestMapping("/{id}")
	public KomplainModel getPasienById(@PathVariable("id") long id) {
		return komplainService.getKomplainById(id);
	}
}
