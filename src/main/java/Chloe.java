import java.util.*;

/**
 * The {@code Chloe} class is a simple interactive program where Chloe
 * greets the user, responds to commands, and says goodbye upon exiting.
 * The program runs until the user types "bye".
 */
public class Chloe {
    // Line used when printing replies
    private static final String LINE = "\t**********************************************";
    private static boolean isRunning = true; // Flag to keep program running
    private static List<String> taskList = new ArrayList<>(); // List of tasks

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
     * Reads in task from the user.
     * Echoes the task string and adds to the list.
     * */
    public static void addTask(String task) {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\t added "+task+" for youu <3");
        System.out.println(LINE);

        // Adds task to the list
        taskList.add(task);
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
            String userEntry = scanner.nextLine();

            // Exit the program when "bye" is typed
            switch(userEntry.toLowerCase()) {
            case "bye":
                isRunning = false;
                break;
            case "list":
                //listTasks(); //TODO: finish
                break;
            default:
                addTask(userEntry);
            }
        }

        sayBye(); // Say goodbye
    }
}
