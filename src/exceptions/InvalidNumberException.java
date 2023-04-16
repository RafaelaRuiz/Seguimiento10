package exceptions;

public class InvalidNumberException extends RuntimeException{
    public InvalidNumberException(){
        super("El numero no debe ser negativo");
    }
}
