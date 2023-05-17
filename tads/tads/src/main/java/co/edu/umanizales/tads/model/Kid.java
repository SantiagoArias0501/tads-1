package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Kid {
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
    @NotNull(message = "debe llenar este campo")
    private Location location;


}
