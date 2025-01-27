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
    private static List<Task> taskList = new ArrayList<>(); // List of tasks

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
        Task newTask = new Task(task);
        taskList.add(newTask);
    }

    /**
     * Lists the tasks stored thus far.
     * Prints out the tasks line by line.
     * */
    public static void listTasks() {
        System.out.println(LINE);
        System.out.println("\t your things to do aree: ");
        for(int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("\t Thing "+(i+1)+". "+task.taskDescription
                                +" ["+task.getStatusIcon()+"]");
        }
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
            // Read user entries
            Scanner scanner = new Scanner(System.in);
            String userEntry = scanner.nextLine();
            String[] stringParts = userEntry.split(" ", 2);
            String userCommand = stringParts[0];
            String entryRemainder = stringParts.length > 1? stringParts[1] : "";

            // NOTE: using switch statement cos at some point it would be better
            // to have an "add" command instead of just taking in everything IMO
            switch(userEntry.toLowerCase()) {
                // This should be userCommand at some pt
            case "bye": // Exit the program when "bye" is typed
                isRunning = false;
                break;
            case "list":
                listTasks();
                break;
            default:
                addTask(userEntry);
            }
        }

        sayBye(); // Say goodbye
    }
}
