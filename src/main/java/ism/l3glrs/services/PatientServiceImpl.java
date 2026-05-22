package ism.l3glrs.services;

import java.util.List;

import ism.l3glrs.entity.Patient;
import ism.l3glrs.repository.PatientRepository;

public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Long creerPatient(Patient patient) {
        return patientRepository.insert(patient);
    }

    @Override
    public List<Patient> listerPatients() {
        return patientRepository.selectAll();
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id);
    }
    
}
