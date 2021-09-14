package com.appnutrix.specialty.domain;

import com.appnutrix.nutritionist.domain.Nutritionist;
import com.appnutrix.specialty.domain.Specialty;

import java.util.List;
import java.util.Optional;


public interface ISpecialtyService /*extends CrudService<Specialty>*/{

    public Specialty save(Specialty specialty) throws Exception;
    void delete(Integer id) throws Exception;
    List<Specialty> getAll() throws  Exception;
    Optional<Specialty> getById(Integer id) throws Exception;
    public List<Specialty> findByInstitutionName(String institution_name) throws Exception;
    public Specialty findByNameAndInstitution(String name, String institution_name) throws Exception;
}
