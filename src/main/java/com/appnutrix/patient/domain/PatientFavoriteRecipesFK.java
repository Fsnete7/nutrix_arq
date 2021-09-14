package com.appnutrix.patient.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PatientFavoriteRecipesFK implements Serializable {

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "recipe_id", nullable = false)
    private Integer recipeId;
}