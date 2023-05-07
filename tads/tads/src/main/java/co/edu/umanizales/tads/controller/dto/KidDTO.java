package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class KidDTO {
    @NotBlank
    @Size(max = 13)
    private String identification;
    @NotBlank
    @Size(max = 20)
    private String name;
    @NotNull
    @Min(1)
    @Max(15)
    private byte age;
    @Pattern(regexp = "^[MF]^",message = "el genero debe ser 'M' o 'f'")
    private char gender;
    @Valid
    @NotNull
    private Location location;

    public String getCodeLocation() {
        return null;
    }
}
