package com.appnutrix.patient.domain;

import com.appnutrix.recipe.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="patient_favorite_recipes")
@NoArgsConstructor
@AllArgsConstructor
public class PatientFavoriteRecipes{

    @EmbeddedId
    private PatientFavoriteRecipesFK patientFavoriteRecipesFK;

    @ManyToOne
    @MapsId("patientId")
    private Patient patient;

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;

    @Column(name = "added_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedAt;
}