package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.ListSEException.ListSEException;
import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
    }
    public void invert(){kids.invert();}
    public void changeExtremes(){kids.changeExtremes();}
    public void add(Kid kid) throws ListSEException {kids.add(kid);}
    public int getCountKidsBylocationsCode(String code){return kids.getCountKidsByLocationCode(code);}
    public void deleteKidbyAge(byte age){kids.deleteByAge(age);}
    public void gainPositionKid(String id,int gain) throws ListSEException {kids.gainPositionKid(id,gain);}
    public void intercaleboyandgirl() throws ListSEException {kids.intercaleboyandgirl();}
    public void losePositionKid(String id , int lose) throws ListSEException {kids.losePositionKid(id, lose);}
    public void orderBoysToStart() throws ListSEException {kids.orderBoysToStart();}
    public float averageAge(){return kids.averageAge();}
    public  String reportByAge(){return kids.reportByAge();}
    public void addToFinalNameChar(String letter) throws ListSEException {kids.addToFinalNameChar(letter);}
    public void getReportKidsByLocationGendersByAge(byte age , ReportKidsLocationGenderDTO report){
        kids.getReportKidsByLocationGendersByAge(age,report);}
    public void verifyId(Kid kid){kids.verifyId(kid);}
}
