package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.EmployerDetails;
import com.qulificationRecomendation.qulificationRecomendation.Repo.EmployerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerDetailsService {

    @Autowired
    private EmployerDetailsRepository employerDetailsRepository;

    public List<EmployerDetails> getAllEmployerDetails() {
        return employerDetailsRepository.findAll();
    }
}