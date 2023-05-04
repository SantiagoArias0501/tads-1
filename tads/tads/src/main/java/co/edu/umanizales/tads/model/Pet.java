package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class Pet {
private String name;
private String bred;

private String identificationDE;
private byte ageDE;
private char genderDE;

public Pet(String name, String bred , byte ageDE){

}
}
