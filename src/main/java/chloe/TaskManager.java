package chloe;
// For collections
import java.util.ArrayList;
import java.util.List;
// For file management
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
// For chloe task management
import chloe.exceptions.IllegalCommandException;
import chloe.exceptions.IncompleteCommandException;
import chloe.tasktypes.Task;
import chloe.tasktypes.Deadline;
import chloe.tasktypes.Event;

public class TaskManager {
    private static final String LINE = "\t**********************************************";
    private static final String DIRECTORY;
    private static final String FILE_NAME;
    private static List<Task> taskList = new ArrayList<>(); // List of tasks


    static {
        // Use the working directory to ensure JAR compatibility
        File baseDir = new File(System.getProperty("user.dir"), "data");
        DIRECTORY = baseDir.getAbsolutePath();
        FILE_NAME = new File(DIRECTORY, "chloeTasks.txt").getAbsolutePath();
    }

    public TaskManager() {
        loadSavedTasks();
    }

    public boolean handleCommand(String userEntry) {
        // Split into commands and details of task
        String[] stringParts = userEntry.split(" ", 2);
        String userCommand = stringParts[0];
        String taskDetails = stringParts.length > 1? stringParts[1] : "";

        // Handle commands
        try {
            switch (userCommand.toLowerCase()) {
            case "bye": // Exit the program when "bye" is typed
                return false;
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
                throw new IllegalCommandException();
            }
        } catch (IllegalCommandException e) {
            handleBadCommand();
        } catch (IncompleteCommandException e) {
            String message = e.getMessage();
            handleIncompleteCommand(message);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            handleBadIndex();
        }
        updateSaveFile(); // Update file after every command

        return true;
    }

    /**
     * Loads the saved tasks from chloeTasks.txt.
     * Checks for the existence of the text file and
     * creates it if not found.
     */
    public static void loadSavedTasks() {
        File directory = new File(DIRECTORY);
        File file = new File(FILE_NAME);

        // Ensure the directory exists
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Failed to create directory: " + DIRECTORY);
            return;
        }

        // Ensure the file exists
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("No saved tasks found. New file created at: " + FILE_NAME);
                }
            } catch (IOException e) {
                System.out.println("Error creating tasks file: " + e.getMessage());
            }
        }

        // Load tasks from file
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" # ");
                if (parts.length < 3) continue;

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                if (type.equals("T")) {
                    task = new Task(description);
                } else if (type.equals("D") && parts.length == 4) {
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E") && parts.length == 5) {
                    task = new Event(description, parts[3], parts[4]);
                } else {
                    continue;
                }

                task.updateStatus(isDone);
                taskList.add(task);
            }
            System.out.println("Tasks loaded successfully from: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + FILE_NAME);
            System.out.println("Error details: " + e.getMessage());
        }
    }

    /**
     * Updates the save file with the new task list.
     */
    private static void updateSaveFile() {
        File file = new File(FILE_NAME);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : taskList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
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
     * Prints message asking user for valid task index.
     */
    public static void handleBadIndex() {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println("\tAdd a task first, then");
        }
        System.out.println("\tEnter a valid task index");
        System.out.println(LINE);
    }

    /**
     * Prints message asking user for valid command.
     * */
    public static void handleIncompleteCommand(String message) {
        System.out.println(LINE);
        System.out.println("\t" + message);
        System.out.println(LINE);
    }

    /**
     * Reads in task from the user and adds it to the task list.
     * */
    public static void addTodo(String task) throws IncompleteCommandException {
        // Adds task to the list
        if (task.isEmpty()) {
            throw new IncompleteCommandException("Please use the format: todo <description>");
        }

        Task newTask = new Task(task);
        newTask.printTaskAddition();
        taskList.add(newTask);
    }

    /**
     * Reads in deadline from the user and adds it to the task list.
     * */
    public static void addDeadline(String task) throws IncompleteCommandException {
        // Split task name and due date
        String[] stringParts = task.split(" /by ", 2);
        String taskDescription = stringParts[0];
        String dueDate = stringParts.length > 1? stringParts[1] : "";

        if (taskDescription.isEmpty() || dueDate.isEmpty()) {
            throw new IncompleteCommandException("Please use the format: deadline <description> /by <due date>");
        }

        // Adds deadline to the list
        Deadline newDeadline = new Deadline(taskDescription, dueDate);
        newDeadline.printTaskAddition();
        taskList.add(newDeadline);
    }

    /**
     * Reads in event from the user and adds it to the task list.
     * */
    public static void addEvent(String task) throws IncompleteCommandException {
        // Split task name and due date
        String[] stringParts = task.split(" /from ", 2);
        String taskDescription = stringParts[0];
        String[] timeParts =
                (stringParts.length > 1? stringParts[1] : "").split(" /to ");
        String from = timeParts[0];
        String to = timeParts.length > 1? timeParts[1] : "";

        if (taskDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IncompleteCommandException("Please use the format: event <description> /from <date/time>"
                    + " /to <end date/time>");
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
     * Marks or unmarks the task specified.
     */
    public static void handleTaskMarking(String markOrUnmark, String[] stringParts) {
        boolean commandIsMark = markOrUnmark.equals("mark");

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
    }
}
