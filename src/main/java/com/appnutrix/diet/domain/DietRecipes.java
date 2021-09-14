package com.appnutrix.diet.domain;

import com.appnutrix.recipe.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="diet_recipes")
@NoArgsConstructor
@AllArgsConstructor
public class DietRecipes {

    @EmbeddedId
    private DietRecipesFK dietRecipesFK;

    @ManyToOne
    @MapsId("dietId")
    private Diet diet;

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;
}