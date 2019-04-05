package com.project.propensib8.repository;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.SurveiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomplainDB extends JpaRepository<KomplainModel, Long>{
    KomplainModel findById(long id);
}
