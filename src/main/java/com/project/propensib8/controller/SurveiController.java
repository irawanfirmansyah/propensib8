package com.project.propensib8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.propensib8.service.SurveiService;

@RestController
@RequestMapping("/survei")
public class SurveiController {
    @Autowired
    private SurveiService surveiService;

    //CRUD controller goes here
}