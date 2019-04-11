package com.project.propensib8.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.ParameterModel;
import com.project.propensib8.model.UnitModel;
import com.project.propensib8.model.UnitParameterModel;
import com.project.propensib8.repository.UnitParameterDB;
import com.project.propensib8.rest.BaseResponse;
import com.project.propensib8.service.ParameterService;
import com.project.propensib8.service.UnitParameterService;
import com.project.propensib8.service.UnitService;
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
	
}
