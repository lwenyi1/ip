package chloe;

import java.util.Scanner;

/**
 * The {@code Chloe} class is a simple interactive program where Chloe.Chloe
 * greets the user, responds to commands, and says goodbye upon exiting.
 * The program runs until the user types "bye".
 */
public class Chloe {
    // Line used when printing replies
    private static final String LINE = "\t**********************************************";
    private static boolean isRunning = true; // Flag to keep program running
    static TaskManager taskManager = new TaskManager();

    /**
     * The main method serves as the entry point of the program.
     * It greets the user, enters an interactive loop to read commands
     * from the console, and exits when the user types "bye".
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        sayHi(); // Greet the user
        Scanner scanner = new Scanner(System.in); // initiate scanner

        // Interactive loop
        while (isRunning) {
            // Read user entries
            if (!scanner.hasNextLine()) { // Avoid reading if no input is available
                break;
            }
            String userEntry = scanner.nextLine();
            isRunning = taskManager.handleCommand(userEntry); // Will return false for termination
        }

        sayBye(); // Say goodbye
    }

    /**
     * Displays a greeting message from Chloe.
     * This method prints a decorative line, a friendly message,
     * and another decorative line.
     */
    public static void sayHi() {
        System.out.println(LINE);
        System.out.println("\tHi! I'm Chloe!");
        System.out.println("\tHow can I help you today?");
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message from Chloe.Chloe.
     * This method prints a decorative line, a farewell message,
     * and another decorative line.
     */
    public static void sayBye() {
        System.out.println(LINE);
        System.out.println("\tOK bye!");
        System.out.println(LINE);
    }

}
