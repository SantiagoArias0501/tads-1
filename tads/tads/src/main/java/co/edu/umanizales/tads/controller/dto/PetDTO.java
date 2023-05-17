package co.edu.umanizales.tads.controller.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data

public class PetDTO {
    private String name;
@Size(max = 30)
    private String race;

    private String identificationDE;

    private String owner;
    @Size(min = 6, max =15 )
    @NotBlank
    private char genderDE;
    private byte ageDE;
    @Pattern(regexp = "^[MF]^",message = "el genero debe ser 'M'o'F'")
    private String codelocation;

}
