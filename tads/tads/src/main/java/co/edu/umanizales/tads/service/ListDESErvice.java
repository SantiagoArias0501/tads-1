package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service

public class ListDESErvice {
    private ListDE pets;

    public ListDESErvice() {pets = new ListDE();}
    public void invertDE(){pets.invertDE();}
    public void addDE(Pet pet){pets.addDE(pet);}
    public void interposeFemaleandMale(){pets.interposeFemaleandMale();}
    public void gainPositionKidDE(String identification,int gainDE){pets.gainPositionKidDE(identification,gainDE);}
    public void LosePositionKidDE(String id , int lose){pets.LosePositionPetDE(id, lose);}
}
