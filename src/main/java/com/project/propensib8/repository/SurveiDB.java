package com.project.propensib8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.propensib8.model.SurveiModel;

@Repository
public interface SurveiDB extends JpaRepository<SurveiModel, Long>{
    SurveiModel findById(long id);
}