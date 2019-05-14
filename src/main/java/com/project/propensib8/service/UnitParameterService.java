package com.project.propensib8.service;

import com.project.propensib8.model.UnitParameterModel;

import java.util.List;
import java.util.Date;

public interface UnitParameterService {
    List<UnitParameterModel> findAllParameterInUnit(String namaUnit);
}
