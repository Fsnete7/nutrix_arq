package com.appnutrix.recipe.domain;

import com.appnutrix.patient.domain.Patient;
import com.appnutrix.recipe.domain.Recipe;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRecipeService {
    public Recipe save(Recipe recipe) throws Exception;
    void delete(Integer id) throws Exception;
    List<Recipe> getAll() throws  Exception;
    Optional<Recipe> getById(Integer id) throws Exception;
    public List<Recipe> findAllByNutritionist(Integer nutritionist_id) throws Exception;
    public Recipe findByName(String name) throws Exception;
    public List<Recipe> findBetweenDates(Date date1, Date date2) throws Exception;

}
