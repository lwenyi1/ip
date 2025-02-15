package chloe;

import java.util.ArrayList;
import java.util.List;

import chloe.exceptions.IllegalCommandException;
import chloe.exceptions.IncompleteCommandException;
import chloe.tasktypes.Task;
import chloe.tasktypes.Deadline;
import chloe.tasktypes.Event;

public class TaskManager {
    private static final String LINE = "\t**********************************************";
    private static List<Task> taskList = new ArrayList<>(); // List of tasks

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
            case "delete":
                deleteTask(stringParts);
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

        return true;
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

    /**
     * Deletes the specified task
     * */
    public static void deleteTask(String[] stringParts) {
        int taskId = Integer.parseInt(stringParts[1])-1;
        Task task = taskList.get(taskId);
        taskList.remove(taskId);

        System.out.println(LINE);
        System.out.println("\tOk! Deleting this task:");
        System.out.println("\t" + task.getStatusIcon() + task.getTaskType()
                + (taskId + 1) + ". " + task.toString());
        System.out.println(LINE);
    }
}
