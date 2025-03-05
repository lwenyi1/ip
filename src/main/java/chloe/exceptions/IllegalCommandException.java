package chloe.exceptions;

/**
 * Thrown when an invalid command is entered by the user.
 */
public class IllegalCommandException extends Exception {
    public IllegalCommandException(String message) {
        super(message);
    }
}
