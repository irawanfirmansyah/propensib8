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


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/komplain")
public class KomplainController {
	
	@Autowired
	private KomplainDB komplainDb;
    
	@GetMapping(value = "/{namaKomplain}")
	public ResponseEntity<?> getKomplainByName(@PathVariable("namaKomplain") String namaKomplain){
		List<KomplainModel> listKomplain = komplainDb.findAll();
		return new ResponseEntity(listKomplain,HttpStatus.OK);
	}
	
    // @GetMapping
    // public ResponseEntity<Object> getAllKomplain(){
    //     List<JSONObject> entities = new ArrayList<>();
    // }
//	@PostMapping(value="/add")
//	public ResponseEntity<?> addUnit(@Valid @RequestBody Map<String,String> result) throws URISyntaxException{
//		String idSurvei = result.get(arg0)
//		UnitModel result = unitDb.save(unit);
//		return ResponseEntity.created(new URI("/survei/add/" + result.getId())).body(result);
//	}
}
