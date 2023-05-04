package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Kid {
    @NotNull
    @NotEmpty
    @Size
    private String identification;
    @NotNull
    @NotEmpty
    @Size
    private String name;
    @Positive
    private byte age;
    @NotNull
    private char gender;
    @Valid
    @NotNull
    private Location location;

    public Kid(String identification,String name , byte age){

    }
}
