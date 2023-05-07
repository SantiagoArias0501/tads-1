package co.edu.umanizales.tads.ListSEException;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestException {
    private final HttpStatus status;

    public RequestException(String code, String message, HttpStatus status){
        this.status = status;
    }

}
