package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.ListSEException.ListDEException;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.PetsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ReportPetsLocationGenderDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.LocationDE;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDESErvice;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/lisde")
public class ListDEController {
    @Autowired
    private ListDESErvice listDEService;

    @Autowired
    private LocationService locationService;

    @GetMapping(path ="/getlistde")
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.putToString(),null), HttpStatus.OK);
    }

    //adicionar mascota
    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO){
        Location locationDE = locationService.getLocationByCode(petDTO.getLocation());
        if (locationDE == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe", null), HttpStatus.OK);
        }
        listDEService.addDE(new Pet(petDTO.getRace(), petDTO.getName(), petDTO.getIdentificationDE(), petDTO.getAgeDE(),
                petDTO.getGenderDE(),petDTO.getOwner(),locationDE));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado la mascota", null),
                HttpStatus.OK);
}
    //ejercicio 1 invertir lista
    @GetMapping(path ="/invertlistde")
    public ResponseEntity<ResponseDTO> invertListDe(){
        listDEService.invertDE();
        return new ResponseEntity<>(new ResponseDTO(
                200,"la lista se ha invertido",null), HttpStatus.OK);
    }

    //ejercicio 2 ordenar machos al comienzo
    @GetMapping(path ="/interposefemaleandmale")
    public ResponseEntity<ResponseDTO> interposeFemaleandMale(){
        listDEService.interposeFemaleandMaleDE();
        return new ResponseEntity<>(new ResponseDTO(
                200,"los machos quedaron al comienzo",null), HttpStatus.OK);
    }

    //ejercicio 3 intercalar macho hembra

    //ejercicio 4  eleminiar una mascota que tenga una edad determinada
    @GetMapping(path ="/deletepet/{age}")
    public ResponseEntity<ResponseDTO> deletePetByAge(@PathVariable byte age){
        listDEService.deletePetbyAgeDE(age);
        return new ResponseEntity<>(new ResponseDTO(
                200,"mascota eliminada",null), HttpStatus.OK);
    }

    //ejercicio 5 promedio de edades
    @GetMapping(path ="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.averageAgeDE(),null), HttpStatus.OK);
    }

    //ejercico 6 ubicaciones

    //obtener mascotas por ubicaciones (pais, departamentos, ciudades)
    @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO> getPetByLocations(){
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listDEService.getCountPetsBylocationsCodeDE(loc.getCode());
            if(count>0){
                petsByLocationDTOList.add(new PetsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,petsByLocationDTOList,
                null), HttpStatus.OK);
    }

    //obtener niños por departamento
    @GetMapping(path = "/petsbydepartament")
    public ResponseEntity<ResponseDTO> getPetsByDepartament(){
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(5)){
            int count = listDEService.getCountPetsBylocationsCodeDE(loc.getCode());

            if(count>0){
                petsByLocationDTOList.add(new PetsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,petsByLocationDTOList,
                null), HttpStatus.OK);
    }

    //niños por ciudad
    @GetMapping(path = "/petsbycity")
    public ResponseEntity<ResponseDTO> getPetsByCity(){
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(8)){
            int count = listDEService.getCountPetsBylocationsCodeDE(loc.getCode());

            if(count>0){
                petsByLocationDTOList.add(new PetsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,petsByLocationDTOList,
                null), HttpStatus.OK);
    }

    //______________________________________________________________________________________________________________

    //ejercicio 7 ganar posicion de mascota
    @GetMapping(path = "/gainpositionpet")
    public ResponseEntity<ResponseDTO>gainPositionById(@RequestBody Map<String, Object> requestBody){
        String id=(String) requestBody.get("id");
        Integer gain=(Integer)requestBody.get("gain");
        listDEService.gainPositionPetDE(id, gain);
        return new ResponseEntity<>(new ResponseDTO(
                200,"la mascota gano posicion",null), HttpStatus.OK);
    }

    // ejercicio 8 perder posicion de la mascota
    @GetMapping(path = "/losepositionpet")
    public ResponseEntity<ResponseDTO>losePositionById(@RequestBody Map<String, Object> requestBody){
        String id=(String) requestBody.get("id");
        Integer lose=(Integer)requestBody.get("lose");
        listDEService.LosePositionKidDE(id, lose);
        return new ResponseEntity<>(new ResponseDTO(
                200,"la mascota perdio posicion",null), HttpStatus.OK);
    }

    //ejercicio 9
    @GetMapping(path = "/reportbyage")
    public ResponseEntity<ResponseDTO>reportByAge(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.reportByAgeDE(),null), HttpStatus.OK);
    }

    //ejercicio 10 enviar niño al final si comienza su nombre por una letra dada
    @GetMapping(path = "/addToFinalNameChar/{letter}")
    public ResponseEntity<ResponseDTO>addToFinalNameChar(@PathVariable String letter) throws ListDEException {
        listDEService.addToFinalPetNameCharDE(letter);
        return new ResponseEntity<>(new ResponseDTO(
                200,"la mascota ahora esta al final de la lista",null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listDEService.changeExtremesDE();
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha intercambiado los extremos",
                null), HttpStatus.OK);
    }

    //obtener una informe de niños por cada ciudad con su respetivo genero
    @GetMapping(path = "/petsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportPetsLocationGenders(@PathVariable byte age) {
        ReportPetsLocationGenderDTO report = new ReportPetsLocationGenderDTO(locationService.getLocationsByCodeSize(8));
        listDEService.getReportPetsByLocationGendersByAgeDE(age,report);
        return new ResponseEntity<>(new ResponseDTO(200,report, null), HttpStatus.OK);
    }
}


