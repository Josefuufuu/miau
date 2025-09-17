package edu.icesi.pensamiento_computacional.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.icesi.pensamiento_computacional.model.Enrollment;
import edu.icesi.pensamiento_computacional.repository.IEnrollmentRepository;
import edu.icesi.pensamiento_computacional.services.IEnrollmentService;


@Service
public class EnrollmentServiceImpl implements IEnrollmentService{

      
    private IEnrollmentRepository enrollmentRepository;
    
    public EnrollmentServiceImpl(IEnrollmentRepository  enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Enrollment> findByTerm(Integer term_id) {
       return enrollmentRepository.findByTerm_Id(term_id);
    }

    
}
