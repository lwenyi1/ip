import java.util.Scanner;

/**
 * The {@code Chloe} class is a simple interactive program where Chloe
 * greets the user, responds to commands, and says goodbye upon exiting.
 * The program runs until the user types "bye".
 */
public class Chloe {
    static boolean isRunning = true;
    static final String LINE = "\t**********************************************";

    /**
     * Displays a greeting message from Chloe.
     * This method prints a decorative line, a friendly message,
     * and another decorative line.
     */
    public static void sayHi() {
        System.out.println(LINE);
        System.out.println("\tOMG HI!! I'm Chloe!!");
        System.out.println("\tHow can I help you todayyy?");
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message from Chloe.
     * This method prints a decorative line, a farewell message,
     * and another decorative line.
     */
    public static void sayBye() {
        System.out.println(LINE);
        System.out.println("\tok BYE!!");
        System.out.println(LINE);
    }

    /**
     * The main method serves as the entry point of the program.
     * It greets the user, enters an interactive loop to read commands
     * from the console, and exits when the user types "bye".
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        sayHi(); // Greet the user

        // Interactive loop
        while (isRunning) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            // Exit the program when "bye" is typed
            if (command.equals("bye")) {
                isRunning = false;
            } else {
                // Echo the command back to the user
                System.out.println(LINE);
                System.out.println("\t"+command);
                System.out.println(LINE);
            }
        }

        sayBye(); // Say goodbye
    }
}
