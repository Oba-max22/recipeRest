package com.obamax.Recipe.service.implementation;

import com.obamax.Recipe.exception.ResourceNotFoundException;
import com.obamax.Recipe.model.Recipe;
import com.obamax.Recipe.repository.RecipeRepository;
import com.obamax.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe Not Found!"));
    }

    @Override
    public Recipe addRecipe(Recipe recipeRequest) {
        return recipeRepository.save(recipeRequest);
    }

    @Override
    public ResponseEntity<?> deleteRecipe(Long recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
