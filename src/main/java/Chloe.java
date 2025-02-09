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

            // Split into commands and details of task
            String[] stringParts = userEntry.split(" ", 2);
            String userCommand = stringParts[0];
            String taskDetails = stringParts.length > 1? stringParts[1] : "";

            // Handle commands
            switch(userCommand.toLowerCase()) {
            case "bye": // Exit the program when "bye" is typed
                isRunning = false;
                break;
            case "list":
                listTasks();
                break;
            case "todo":
                addTodo(taskDetails);
                break;
            case "deadline":
                addDeadline(taskDetails);
                break;
            case "event":
                addEvent(taskDetails);
                break;
            case "mark":
            case "unmark":
                handleTaskMarking(userCommand.toLowerCase(), stringParts);
                break;
            default:
                handleBadCommand();
            }
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
     * Displays a goodbye message from Chloe.
     * This method prints a decorative line, a farewell message,
     * and another decorative line.
     */
    public static void sayBye() {
        System.out.println(LINE);
        System.out.println("\tOK bye!");
        System.out.println(LINE);
    }

    /**
     * Prints message asking user for valid command.
     * */
    public static void handleBadCommand() {
        System.out.println(LINE);
        System.out.println("\tI'm sorry I don't know what you mean!!");
        System.out.println("\tPlease choose a valid command :\")");
        //System.out.println("\tUse help for a list of commands");
        System.out.println(LINE);
    }

    /**
     * Reads in task from the user and adds it to the task list.
     * */
    public static void addTodo(String task) {
        // Adds task to the list
        if (task.isEmpty()) {
            System.out.println(LINE);
            System.out.println("\tPlease use the format: todo <description>");
            System.out.println(LINE);
            return;
        }

        Task newTask = new Task(task);
        newTask.printTaskAddition();
        taskList.add(newTask);
    }

    /**
     * Reads in deadline from the user and adds it to the task list.
     * */
    public static void addDeadline(String task) {
        // Split task name and due date
        String[] stringParts = task.split(" /by ", 2);
        String taskDescription = stringParts[0];
        String dueDate = stringParts.length > 1? stringParts[1] : "";

        if (taskDescription.isEmpty() || dueDate.isEmpty()) {
            System.out.println(LINE);
            System.out.println("\tPlease use the format: deadline <description> /by <due date>");
            System.out.println(LINE);
            return;
        }

        // Adds deadline to the list
        Deadline newDeadline = new Deadline(taskDescription, dueDate);
        newDeadline.printTaskAddition();
        taskList.add(newDeadline);
    }

    /**
     * Reads in event from the user and adds it to the task list.
     * */
    public static void addEvent(String task) {
        // Split task name and due date
        String[] stringParts = task.split(" /from ", 2);
        String taskDescription = stringParts[0];
        String[] timeParts =
                (stringParts.length > 1? stringParts[1] : "").split(" /to ");
        String from = timeParts[0];
        String to = timeParts.length > 1? timeParts[1] : "";

        if (taskDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println(LINE);
            System.out.println("\tPlease use the format: event <description> /from <date/time>" +
                    " /to <end date/time>");
            System.out.println(LINE);
            return;
        }

        // Adds event to the list
        Event newEvent = new Event(taskDescription, from, to);
        newEvent.printTaskAddition();
        taskList.add(newEvent);
    }

    /**
     * Lists the tasks stored thus far.
     * Prints out the tasks line by line.
     * */
    public static void listTasks() {
        System.out.println(LINE);
        System.out.println("\tyou have " + taskList.size() + " things in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("\t"+task.getStatusIcon() + task.getTaskType()
                    +(i+1)+". " + task.toString());
        }
        System.out.println(LINE);
    }

    /**
     * Print error message for marking or unmarking tasks.
     */
    private static void printMarkError() {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println("\tAdd a task first, then");
        }
        System.out.println("\tEnter a valid task index, e.g. mark 1");
        System.out.println(LINE);
    }

    /**
     * Marks or unmarks the task specified.
     */
    public static void handleTaskMarking(String markOrUnmark, String[] stringParts) {
        boolean commandIsMark = markOrUnmark.equals("mark");

        try {
            int taskId = Integer.parseInt(stringParts[1])-1;
            Task task = taskList.get(taskId);
            task.updateStatus(commandIsMark);
            System.out.println(LINE);
            if (commandIsMark) {
                System.out.println("\tMarked \"" + task.getTaskDescription()
                    + "\" as done for you!");
            } else {
                System.out.println("\tOh \"" + task.getTaskDescription()
                        + "\" is not done? Unmarked.");
            }
            System.out.println("\t" + task.getStatusIcon() + task.getTaskType()
                    + (taskId + 1) + ". " + task.toString());
            System.out.println(LINE);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            printMarkError();
        }
    }
}
