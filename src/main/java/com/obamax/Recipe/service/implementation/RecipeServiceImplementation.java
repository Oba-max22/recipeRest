package com.obamax.Recipe.service.implementation;

import com.obamax.Recipe.model.Recipe;
import com.obamax.Recipe.repository.RecipeRepository;
import com.obamax.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
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
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            return recipe.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public Recipe addRecipe(Recipe recipeRequest) {
        recipeRequest.setDate(LocalDateTime.now().toString());
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

    @Override
    public ResponseEntity<?> editRecipe(Long recipeId, Recipe recipeRequest) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if(recipe.isPresent()) {
            recipe.get().setName(recipeRequest.getName());
            recipe.get().setCategory(recipeRequest.getCategory());
            recipe.get().setDescription(recipeRequest.getDescription());
            recipe.get().setIngredients(recipeRequest.getIngredients());
            recipe.get().setDirections(recipeRequest.getDirections());
            recipe.get().setDate(LocalDateTime.now().toString());
            return new ResponseEntity<>(recipeRepository.save(recipe.get()), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Recipe> searchRecipesByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    @Override
    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

}