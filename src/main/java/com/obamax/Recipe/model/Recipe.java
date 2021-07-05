package com.obamax.Recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recipe")
public class Recipe implements Serializable {
    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @ElementCollection
    @Size(min = 1)
    private List<String> ingredients = new ArrayList<>();

    @ElementCollection
    @Size(min = 1)
    private List<String> directions = new ArrayList<>();

}
