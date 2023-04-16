package exceptions;

public class NotTypeMedalException extends RuntimeException{
    public NotTypeMedalException(){
        super("El tipo de medalla inscrito, no es: ni oro, plata o bronce");
    }
}
