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
    public static void addTask(String task) {
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

        // Adds task to the list
        Deadline newDeadline = new Deadline(taskDescription, dueDate);
        newDeadline.printTaskAddition();
        taskList.add(newDeadline);
    }

    /**
     * Lists the tasks stored thus far.
     * Prints out the tasks line by line.
     * */
    public static void listTasks() {
        System.out.println(LINE);
        System.out.println("\tyour things to do aree: ");
        for(int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("\t"+task.getStatusIcon() + "Thing "+(i+1)+". "
                    + task.getTaskType() + " "+task.toString());
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
        System.out.println("\tYAY marked \""+task.getTaskDescription()
                            +"\" as done for you <3");
        System.out.println("\t"+task.getStatusIcon() + "Thing "+(taskId+1)+". "
                + task.getTaskType() + " "+task.toString());
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
        System.out.println("\tHUH \""+task.getTaskDescription()
                +"\" is not done? Unmarked :(");
        System.out.println("\t"+task.getStatusIcon() + "Thing "+(taskId+1)+". "
                + task.getTaskType() + " "+task.toString());
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
            String entryRemainder = stringParts.length > 1? stringParts[1] : "";

            // Handle commands
            switch(userCommand.toLowerCase()) {
            case "bye": // Exit the program when "bye" is typed
                isRunning = false;
                break;
            case "list":
                listTasks();
                break;
            case "todo":
                addTask(entryRemainder);
                break;
            case "deadline":
                addDeadline(entryRemainder);
                break;
            case "event":
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
