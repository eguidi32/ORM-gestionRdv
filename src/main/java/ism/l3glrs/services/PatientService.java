package ism.l3glrs.services;

import java.util.List;

import ism.l3glrs.entity.Patient;

public interface PatientService {
    Long creerPatient(Patient patient);
    List<Patient> listerPatients();
    Patient findById(Long id);
}
