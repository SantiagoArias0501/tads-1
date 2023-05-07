package co.edu.umanizales.tads.controller.dto;


import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.LocationDE;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportPetsLocationGenderDTO {
    private List<LocationGenderQuantityDTO> locationGenderQuantityDTOS;

    public ReportPetsLocationGenderDTO(List<Location> cities) {
        locationGenderQuantityDTOS = new ArrayList<>();
        for (Location location : cities) {
            locationGenderQuantityDTOS.add(new LocationGenderQuantityDTO
                    (location.getName()));

        }
    }

    public void updateQuantity(String name, char genderDE) {

    }
}
