package co.edu.umanizales.tads.ListSEException;

public class ListSEException extends Exception{
    private final String code;

    public ListSEException (String code , String message ){
        super(message);
        this.code = code;
    }

}
