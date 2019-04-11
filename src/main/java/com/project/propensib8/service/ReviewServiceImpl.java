package com.project.propensib8.service;

import com.project.propensib8.repository.ReviewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewDB reviewDb;

    @Override
    public int countReviewByNama(String nama) {
        int count = 0;
        for(int num = 0; num < reviewDb.findAll().size(); num++){
            if(reviewDb.findAll().get(num).getUnit().getNama().equalsIgnoreCase(nama)){
                count += 1;
            }
        }
        return count;
    }
}
