package com.obamax.Recipe.dto;

import lombok.Data;

@Data
public class RecipeDto {
    private String name;
    private String description;
    private String[] ingredients;
    private String[] directions;
}
