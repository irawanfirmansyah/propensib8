package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
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
import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.model.SurveiModel;
import com.project.propensib8.model.UnitModel;
import com.project.propensib8.repository.KomplainDB;
import com.project.propensib8.rest.BaseResponse;
import com.project.propensib8.service.KomplainService;
import com.project.propensib8.service.SurveiService;
import com.project.propensib8.service.UnitService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/komplain")
public class KomplainController {	
	@Autowired
	private KomplainDB komplainDb;
	@Autowired
	private KomplainService komplainService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private SurveiService surveiService;

	@GetMapping(value = "/{namaKomplain}")
	public ResponseEntity<?> getKomplainByName(@PathVariable("namaKomplain") String namaKomplain){
		List<KomplainModel> listKomplain = komplainDb.findAll();
		return new ResponseEntity(listKomplain,HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public KomplainModel getPasienById(@PathVariable("id") long id) {
		return komplainService.getKomplainById(id);
	}

	@PostMapping(value="/add")
	public ResponseEntity<?> createKomplain(@Valid @RequestBody Map<String, String> res) throws URISyntaxException, ParseException{
		
		KomplainModel result = new KomplainModel();
		SurveiModel surveiModel = surveiService.getSurveiById(res.get("idSurvei"));
		UnitModel unitModel = unitService.getUnitByName(res.get("namaUnit"));
		
		result.setDeskripsi(res.get("deskripsi"));
		result.setUnit(unitModel);
		result.setSurvei(surveiModel);
		result.setSolved(false);
		result.setResult("");
		result = komplainDb.save(result);
		BaseResponse<KomplainModel> response = new BaseResponse<>();
		
		if(result.getId() != 0) {
			response.setStatus(200);
			response.setMessage("success");
			response.setResult(result);
		}
		
//		return ResponseEntity.created(new URI("/survei/add/" + result.getId())).body(result);
		return ResponseEntity.created(new URI("/survei")).body(response);
	}
	
}
