package chloe;

/**
 * The {@code Chloe} class is a simple interactive program where Chloe.Chloe
 * greets the user, responds to commands, and says goodbye upon exiting.
 * The program runs until the user types "bye".
 *
 * @author Wenyi
 * @version 1.0
 * @since 2025-01-31
 */
public class Chloe {
    static Ui ui = new Ui();
    /**
     * The main method serves as the entry point of the program.
     */
    public static void main(String[] args) {
        ui.run();
    }

}
