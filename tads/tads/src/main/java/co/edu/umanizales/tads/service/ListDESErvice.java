package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.ListSEException.ListDEException;
import co.edu.umanizales.tads.controller.dto.ReportPetsLocationGenderDTO;
import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.Pet;
import org.springframework.stereotype.Service;

@Service

public class ListDESErvice {
    private ListDE pets;
    public String putToString(){return pets.toString();}

    public ListDESErvice() {pets = new ListDE();}
    public void invertDE(){pets.invertDE();}
    public void addDE(Pet pet){pets.addDE(pet);}
    public void interposeFemaleandMaleDE(){pets.interposeFemaleandMale();}
    public void gainPositionPetDE(String identification,int gainDE){pets.gainPositionKidDE(identification,gainDE);}
    public void LosePositionKidDE(String id , int lose){pets.LosePositionPetDE(id, lose);}
    public void changeExtremesDE() {pets.changeExtremesDE();}
    public void addToFinalPetNameCharDE(String letter) throws ListDEException {pets.addToFinalNameCharDE(letter);}
    public void deletePetbyAgeDE(byte age){pets.deletePetByAgeDE(age);}
    public void getReportPetsByLocationGendersByAgeDE(byte age , ReportPetsLocationGenderDTO report){
        pets.getReportKidsByLocationGendersByAge(age,report);}
    public float averageAgeDE(){return pets.averageAgeDE();}
    public int getCountPetsBylocationsCodeDE(String code){return pets.getCountKidsByLocationCode(code);}
    public  String reportByAgeDE(){return pets.reportByAgeDE();}
}
