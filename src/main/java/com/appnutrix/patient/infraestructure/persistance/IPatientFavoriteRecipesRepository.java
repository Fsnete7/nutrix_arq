package com.appnutrix.patient.infraestructure.persistance;

import com.appnutrix.patient.domain.PatientFavoriteRecipes;
import com.appnutrix.patient.domain.PatientFavoriteRecipesFK;

import com.appnutrix.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPatientFavoriteRecipesRepository extends JpaRepository<PatientFavoriteRecipes, PatientFavoriteRecipesFK> {
    @Query("Select b.recipe from PatientFavoriteRecipes b where b.patient.id = :patient_id")
    public List<Recipe> findByPatient(@Param("patient_id") Integer patient_id);
}