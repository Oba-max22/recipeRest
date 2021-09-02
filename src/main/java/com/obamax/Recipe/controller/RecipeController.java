package com.obamax.Recipe.controller;

import com.obamax.Recipe.model.Recipe;
import com.obamax.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok().body(recipe);
    }

    @PostMapping("/new")
    public ResponseEntity<?> AddNewRecipe (@Valid
                                           @RequestBody Recipe recipeRequest){
        Recipe recipe = recipeService.addRecipe(recipeRequest);
        String response = String.format("{ id : %d }", recipe.getId());
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable Long id){
        return recipeService.deleteRecipe(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long id,
                                          @Valid
                                          @RequestBody Recipe recipeRequest) {
        return recipeService.editRecipe(id, recipeRequest);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Recipe>> getBySearch(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String category) {
        if (name != null) {
            name = name.toLowerCase(Locale.ROOT);
            return ResponseEntity.ok(recipeService.searchRecipesByName(name));
        }
        if (category != null) {
            category = category.toLowerCase(Locale.ROOT);
            return ResponseEntity.ok(recipeService.searchRecipesByCategory(category));
        }
        return ResponseEntity.badRequest().build();
    }

}
