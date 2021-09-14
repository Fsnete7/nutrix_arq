package com.appnutrix.nutritionist.domain;

import java.util.List;
import java.util.Optional;

public interface INutritionistService {
    public Nutritionist save(Nutritionist nutritionist) throws Exception;
    void delete(Integer id) throws Exception;
    List<Nutritionist> getAll() throws  Exception;
    Optional<Nutritionist> getById(Integer id) throws Exception;
    public Nutritionist findByUsername(String username) throws Exception;
    public Nutritionist findByCnpNumber(Integer cnp_number) throws Exception;
    public List<Nutritionist> findByFirstName(String firstname) throws Exception;
    public List<Nutritionist> findByLastName(String lastname) throws Exception;
    public List<Nutritionist> findByFirstNameAndLastName(String firstname, String lastname) throws Exception;
}