
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @JsonIgnore
    private String id;

    @NotNull
    private String restaurantId;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String imageUrl;

    @NotNull
    @Max(value = 90, message = "Latitude should not be greater than 90")
    private Double latitude;

    @NotNull
    @Max(value = 180, message = "Longitude should not be greater than 180")
    private Double longitude;

    @NotNull
    private String opensAt;

    @NotNull
    private String closesAt;
    
    @NotNull
    private List<String> attributes;

}

