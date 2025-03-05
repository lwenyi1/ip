package chloe.exceptions;

/**
 * Thrown when an incomplete command is entered by the user.
 */
public class IncompleteCommandException extends Exception{
    private final String message;

    public IncompleteCommandException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
