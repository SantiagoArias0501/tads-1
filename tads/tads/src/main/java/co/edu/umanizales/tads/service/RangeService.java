package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.RangeAgeKidDTO;
import co.edu.umanizales.tads.model.Range;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RangeService {
    private List<Range>ranges;
    public RangeService(){
        ranges= new ArrayList<>();
        ranges.add(new Range(1,3));
        ranges.add(new Range(4,6));
        ranges.add(new Range(7,9));
        ranges.add(new Range(10,12));
        ranges.add(new Range(14,15));
    }

}
