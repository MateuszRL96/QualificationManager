package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Entity.Qualification;
import com.qulificationRecomendation.qulificationRecomendation.Repo.QualificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationService {
    private final QualificationRepository qualificationRepository;

    public QualificationService(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Transactional
    public Qualification addQualification(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Transactional(readOnly = true)
    public Qualification getQualificationById(Long id) {
        Optional<Qualification> qualification = qualificationRepository.findById(id);
        if (qualification.isPresent()) {
            return qualification.get();
        } else {
            throw new EntityNotFoundException("Qualification with id " + id + " not found");
        }
    }

    public List<Qualification> getAllQualifications() {
        return qualificationRepository.findAll();
    }

    public void deleteQualification(Long id) {
        qualificationRepository.deleteById(id);
    }

    public Optional<Qualification> getQualificationWithSkills(Long id) {
        return qualificationRepository.findById(id);
    }
}
