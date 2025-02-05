package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.CvData;
import com.qulificationRecomendation.qulificationRecomendation.Repo.CvDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvDataService {

    @Autowired
    private CvDataRepository cvDataRepository;

    public CvData saveCvData(CvData cvData) {
        return cvDataRepository.save(cvData);
    }
}