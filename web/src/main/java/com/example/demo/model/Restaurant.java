package com.example.demo.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @Size(max = 500)
    private String description;
    private String short_description;
    private String picture1;
    private String picture2;
    @NotNull
    private int capacity;
    private String status;
    private String city;
    private String slug_city;

    public String getName() {
        return name;
    }
}

