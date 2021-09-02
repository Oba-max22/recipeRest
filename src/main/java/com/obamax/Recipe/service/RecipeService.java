package com.obamax.Recipe.service;

import com.obamax.Recipe.model.Recipe;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface RecipeService {
    Recipe getRecipeById(Long recipeId);
    Recipe addRecipe(Recipe recipeRequest);
    ResponseEntity<?> deleteRecipe(Long recipeId);
    ResponseEntity<?> editRecipe(Long recipeId, Recipe recipeRequest);
    List<Recipe> searchRecipesByCategory(String category);
    List<Recipe> searchRecipesByName(String name);
}
