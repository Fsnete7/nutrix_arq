package com.appnutrix.patient.domain;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    public Patient save(Patient patient) throws Exception;
    void delete(Integer id) throws Exception;
    List<Patient> getAll() throws  Exception;
    Optional<Patient> getById(Integer id) throws Exception;
    public Patient findByUsername (String username) throws Exception;
    public List<Patient> findByLastName(String lastname) throws Exception;
    public List<Patient> findByFirstNameAndLastName(String firstname, String lastname) throws Exception;
    public List<Patient> findByFirstName(String firstName) throws Exception;
}