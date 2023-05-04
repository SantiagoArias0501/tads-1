package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Range;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RangeAgeKidDTO {
    private Range range;
    int quantity;
}
