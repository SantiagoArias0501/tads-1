package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.ListSEException.ListSEException;
import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @Autowired
    private LocationService locationService;



    //adicionar niños
    @PostMapping(path = "/addkid")
    public ResponseEntity<ResponseDTO> addKid(@Valid @RequestBody KidDTO kidDTO) throws ListSEException {
        Location location = locationService.getLocationByCode(kidDTO.getCodelocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listSEService.add(new Kid(kidDTO.getIdentification(), kidDTO.getName(), kidDTO.getAge(),
                kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el petacón", null),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);
    }


    //ejercicio 1 invertir lista
    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    //ejercicio 2 niños al comienzo
    @GetMapping(path = "/orderboystostart")
    public ResponseEntity<ResponseDTO>orderBoysToStart() throws ListSEException {
        listSEService.orderBoysToStart();
        return new ResponseEntity<>(new ResponseDTO(
                200,"niños ordenados al comienzo",null), HttpStatus.OK);
    }

    //ejercicio 3 intercalar niño niña
    @GetMapping(path = "/intercaleboyandgirl")
    public ResponseEntity<ResponseDTO>IntercalateBoyGirl() throws ListSEException {
        listSEService.intercaleboyandgirl();
        return new ResponseEntity<>(new ResponseDTO(
                200,"los niños fueron intercalados",null), HttpStatus.OK);
    }

    // ejercicio 4 borrar niño por edad
    @GetMapping(path = "/deletekid/{age}")
    public ResponseEntity<ResponseDTO>deleteKidByAge(@PathVariable byte age){
        listSEService.deleteKidbyAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200,"niño eliminado",null), HttpStatus.OK);
    }

    //ejercicio 5 promedio de edades
    @GetMapping(path = "/averageage")
    public ResponseEntity<ResponseDTO>AverageAge(){
        return new ResponseEntity<>(new ResponseDTO(
                200,(float)listSEService.averageAge(),null), HttpStatus.OK);
    }

    //ejercico 6 ubicaciones___________________________________________________________________________________________

    //obtener niños por ubicaciones (pais, departamentos, ciudades)
    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByCity(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    //obtener niños por departamento
    @GetMapping(path = "/kidsbydepartament")
    public ResponseEntity<ResponseDTO> getKidsByDepartament(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(5)){
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());

            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    //niños por ciudad
    @GetMapping(path = "/kidsbycity")
    public ResponseEntity<ResponseDTO> getKidsBylocationsCode(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(8)){
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,kidsByLocationDTOList, null), HttpStatus.OK);
    }

    //______________________________________________________________________________________________________________

    //ejercicio 7 ganar posicion de niño
    @PostMapping(path = "/gainpositionkid")
    public ResponseEntity<ResponseDTO>gainPositionById(@RequestBody Map<String, Object> requestBody) throws ListSEException {
        String identification=(String) requestBody.get("identification");
        Integer gain=(Integer)requestBody.get("gain");
        listSEService.gainPositionKid(identification, gain);
        return new ResponseEntity<>(new ResponseDTO(
                200,"el niño gano posicion",null), HttpStatus.OK);
    }

    // ejercicio 8 perder posicion de niño
    @PostMapping(path = "/losepositionskid")
    public ResponseEntity<ResponseDTO> losePositionkid(@RequestBody Map<String, Object> requestBody) throws ListSEException {
        String id = (String) requestBody.get("id");
        Integer lose = (Integer) requestBody.get("lose");
        listSEService.losePositionKid(id,lose);
        return new ResponseEntity<>(new ResponseDTO(
                200, "posiciones re ordenadas", null), HttpStatus.OK);
}

    //ejercicio 9
    @GetMapping(path = "/reportbyage")
    public ResponseEntity<ResponseDTO>reportByAge(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.reportByAge(),null), HttpStatus.OK);
    }


    //ejercicio 10 enviar niño al final si comienza su nombre por una letra dada
    @PostMapping(path = "/addToFinalNameChar/{letter}")
    public ResponseEntity<ResponseDTO>addToFinalNameChar(@RequestBody String letter) throws ListSEException {
        listSEService.addToFinalNameChar(letter);
        return new ResponseEntity<>(new ResponseDTO(
                200,"el niño ahora esta al final de la lista",null), HttpStatus.OK);
    }


    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    //obtener una informe de niños por cada ciudad con su respetivo genero
    @GetMapping(path = "/kidsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportKisLocationGenders(@PathVariable byte age) {
        ReportKidsLocationGenderDTO report =
                new ReportKidsLocationGenderDTO(locationService.getLocationsByCodeSize(8));
        listSEService.getReportKidsByLocationGendersByAge(age, report);
        return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
    }

    @GetMapping (path = "/verifyid")
    public ResponseEntity<ResponseDTO>getVerifyid(Kid kid){
        listSEService.verifyId(kid);
        return new ResponseEntity<>(new ResponseDTO(200,"verificado",null),HttpStatus.OK);
    }
}