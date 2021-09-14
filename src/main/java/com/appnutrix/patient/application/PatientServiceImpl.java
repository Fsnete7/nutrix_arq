package com.appnutrix.patient.application;


import com.appnutrix.patient.infraestructure.persistance.IPatientRepository;
import com.appnutrix.patient.domain.IPatientService;
import com.appnutrix.patient.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public List<Patient> findByLastName(String lastname) throws Exception {
        return patientRepository.findByLastName(lastname);
    }

    @Override
    public List<Patient> findByFirstNameAndLastName(String firstname, String lastname) throws Exception {
        return patientRepository.findByFirstNameAndLastName(firstname, lastname);
    }

    @Override
    public List<Patient> findByFirstName(String firstName) throws Exception {
        return patientRepository.findByFirstName(firstName);
    }

    @Override
    public Patient findByUsername (String username) throws Exception {
        return patientRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public Patient save(Patient patient) throws Exception {
        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAll() throws Exception {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getById(Integer id) throws Exception {
        return patientRepository.findById(id);
    }
}