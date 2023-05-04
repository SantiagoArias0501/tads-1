package co.edu.umanizales.tads.controller.dto;

import jdk.jfr.Name;
import lombok.Data;

@Data
public class PetDTO {
    private String name;
    private String bred;

    private int identificationDE;

    private String owner;

    private char genderDE;

}
