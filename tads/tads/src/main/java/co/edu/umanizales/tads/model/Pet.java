package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
private String name;
private String bred;

private String identificationDE;
private byte ageDE;
private char genderDE;
private String owner;
private Location location;

}
