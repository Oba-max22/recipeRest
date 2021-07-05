package com.obamax.Recipe.service;

import com.obamax.Recipe.model.Recipe;
import org.springframework.http.ResponseEntity;

public interface RecipeService {
    Recipe getRecipeById(Long recipeId);

    Recipe addRecipe(Recipe recipeRequest);

    ResponseEntity<?> deleteRecipe(Long recipeId);
}
