package com.project.propensib8.controller;

import com.project.propensib8.model.SurveiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.project.propensib8.service.SurveiService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/survei")
public class SurveiController {
	@Autowired
	private SurveiService surveiService;

//	@GetMapping(value = "/{id}")
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }

    @RequestMapping("/")
    public String getHome() {
        return "AAAAAA";
    }

    @RequestMapping("/{id}")
    public SurveiModel getPasien(@PathVariable("id") long id) {
		SurveiModel result = surveiService.getSurveiById(id);
		return result;
	}
	
}
