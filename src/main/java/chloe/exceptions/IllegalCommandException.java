package chloe.exceptions;

// For invalid command inputs
public class IllegalCommandException extends Exception {
    public IllegalCommandException(String message) {
        super(message);
    }
}
