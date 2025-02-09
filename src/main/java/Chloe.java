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
        System.out.println("\tEnter a valid command please :\")");
        System.out.println(LINE);
    }

    /**
     * Reads in task from the user and adds it to the task list.
     * */
    public static void addTodo(String task) {
        // Adds task to the list
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
     * Marks task as done.
     * Updates the task status and informs user.
     * */
    public static void markTask(int taskId) {
        Task task = taskList.get(taskId);
        task.updateStatus(true);
        System.out.println(LINE);
        System.out.println("\tMarked \""+task.getTaskDescription()
                            +"\" as done for you!");
        System.out.println("\t"+task.getStatusIcon() + task.getTaskType()
                +(taskId+1)+". " + task.toString());
        System.out.println(LINE);
    }

    /**
     * Unmarks task as done.
     * Updates the task status and informs user.
     * */
    public static void unmarkTask(int taskId) {
        Task task = taskList.get(taskId);
        task.updateStatus(false);
        System.out.println(LINE);
        System.out.println("\tOh \""+task.getTaskDescription()
                +"\" is not done? Unmarked.");
        System.out.println("\t"+task.getStatusIcon() + task.getTaskType()
                +(taskId+1)+". " + task.toString());
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

            // Implement different commands
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
                int markTaskId = stringParts.length > 1 ?
                        (Integer.parseInt(stringParts[1])-1) : -1;
                markTask(markTaskId);
                break;
            case "unmark":
                int unmarkTaskId = stringParts.length > 1 ?
                        (Integer.parseInt(stringParts[1])-1) : -1;
                unmarkTask(unmarkTaskId);
                break;
            default:
                handleBadCommand();
            }
        }

        sayBye(); // Say goodbye
    }
}
