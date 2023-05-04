package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.ListSEException.ListSEException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Range;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(path="/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids().getHead(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) {
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "la ubicacion no existe", null)
                    , HttpStatus.OK);
        }
        listSEService.add(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado el Kid",
                null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha invertido la lista",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> ChangeExtremes() {
        listSEService.changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha intercambiado los extremos"
                , null), HttpStatus.OK);
    }



    @GetMapping(path = "/kidsbylocations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int male = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int female = listSEService.getCountKidsBylocationsCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbyDepartament")
    public ResponseEntity<ResponseDTO> getKidsByDepartament() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocationsByCodeSize(5)) {
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int male = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int female = listSEService.getCountKidsBylocationsCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, kidsByLocationDTOList, null)
                , HttpStatus.OK);
    }

    //niños por ciudad
    @GetMapping(path = "/kidsbycity")
    public ResponseEntity<ResponseDTO> getKidsBylocationsCode() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocationsByCodeSize(8)) {
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return  new ResponseEntity<>(new ResponseDTO(200 , kidsByLocationDTOList,null),HttpStatus.OK);
    }
        @GetMapping(path = "/average")
        public ResponseEntity<ResponseDTO>averageAge(){
        float averageAge = listSEService.getKids().averageAge();
        return new ResponseEntity<>(new ResponseDTO(200,
                "la edad promedio de los niños es:"+averageAge,null),HttpStatus.OK);
        }
        @PostMapping(path ="lostposition")
        public ResponseEntity<ResponseDTO>loseposition(@RequestBody Map<String, Object>requestBody){
        String id = (String) requestBody.get("id");
        Integer lose = (Integer) requestBody.get("lose");
        listSEService.LosePositionKid(id,lose);
        return new ResponseEntity<>(new ResponseDTO(200,"posicones re ordenadas",null),HttpStatus.OK);
        }





    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> DeleteByAge(@PathVariable byte age){
        listSEService.deleteKidbyAge(age);
        return new ResponseEntity<>(new ResponseDTO(200,"niños eliminados ", null), HttpStatus.OK);

    }

    @GetMapping(path = "/IntercaleBoyandGirl")
    public ResponseEntity<ResponseDTO> IntercaleBoyandGirl(){
        listSEService.IntercaleBoyandGirl();
        return new ResponseEntity<>(new ResponseDTO(200,"niños intercalados ", null), HttpStatus.OK);

    }

    @GetMapping(path = "/gainpositionkid")
    public ResponseEntity<ResponseDTO>GainPositionKid(String id , int Advance){
        listSEService.gainPositionKid(id, Advance);
        return new ResponseEntity<>(new ResponseDTO(200,"el niño ah ganado posicion",null),HttpStatus.OK);
    }
    @GetMapping(path = "/addtofinalnamechar")
    public ResponseEntity<ResponseDTO>addToFinalNameChar(String letra){
        try {
            listSEService.getKids().addToFinalNameChar(letra);
            } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"se han agregado al inicio los nombres que inician "
                ,null),HttpStatus.OK);
    }
    @GetMapping(path = "/Rangeage")
    public  ResponseEntity<ResponseDTO>getRangeByKids(){
        List<RangeAgeKidDTO>kidRangeDTOList = new ArrayList<>();
        for (Range i : RangeService.getrange()){
            int quantity = listSEService.getKids().rangeByAge(i.getFrom(),i.getTo());
            kidRangeDTOList.add(new RangeAgeKidDTO(i,quantity));
        }
        return new ResponseEntity<>(new ResponseDTO(200,kidRangeDTOList,null),HttpStatus.OK);
    }

}


