package com.cicosy.crm.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityData {
    private String name;
    private Long id;

}
