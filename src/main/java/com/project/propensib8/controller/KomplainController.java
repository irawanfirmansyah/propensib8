package com.project.propensib8.controller;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.service.KomplainService;
import com.project.propensib8.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/komplain")
public class KomplainController {

    @Autowired
    private KomplainService komplainService;

    @RequestMapping("/")
    public List<KomplainModel> getAllSuvei() {
        return komplainService.findAll();
    }

    @RequestMapping("/{id}")
    public KomplainModel getPasienById(@PathVariable("id") long id) {
        return komplainService.getSurveiById(id);
    }
}
