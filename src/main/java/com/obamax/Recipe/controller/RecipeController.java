package com.obamax.Recipe.controller;

import com.google.gson.Gson;
import com.obamax.Recipe.dto.RecipeDto;
import com.obamax.Recipe.model.Recipe;
import com.obamax.Recipe.service.implementation.RecipeServiceImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RecipeController {
    private final RecipeServiceImplementation recipeServiceImplementation;
    private final ModelMapper modelMapper;
    private final Gson gson = new Gson();

    public RecipeController(RecipeServiceImplementation recipeServiceImplementation, ModelMapper modelMapper) {
        this.recipeServiceImplementation = recipeServiceImplementation;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeServiceImplementation.getRecipeById(id);
        RecipeDto recipeResponse = modelMapper.map(recipe, RecipeDto.class);
        return ResponseEntity.ok().body(recipeResponse);
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> AddNewRecipe (@Valid
                                           @RequestBody Recipe recipeRequest){
        Recipe recipe = recipeServiceImplementation.addRecipe(recipeRequest);
        Map<String, Long> mapResponse = new HashMap<>();
        mapResponse.put("id", recipe.getId());
        String recipeResponse = gson.toJson(mapResponse);
        return ResponseEntity.ok().body(recipeResponse);
    }
    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable Long id){
        return recipeServiceImplementation.deleteRecipe(id);
    }

}
