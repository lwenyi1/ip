package chloe;
// For collections
import java.util.ArrayList;
import java.util.List;

// For chloe task management
import chloe.commands.AddDeadline;
import chloe.commands.AddEvent;
import chloe.commands.AddTodo;
import chloe.commands.ListTasks;
import chloe.exceptions.IllegalCommandException;
import chloe.exceptions.IncompleteCommandException;
import chloe.exceptions.ParseErrorException;
import chloe.tasktypes.Task;
import chloe.tasktypes.Deadline;
import chloe.tasktypes.Event;

public class Parser {
    private static final String LINE = "\t**********************************************";
    TaskList taskList;

    public Parser() {
        taskList = new TaskList();
        Storage.loadSavedTasks(taskList);
    }

    public void handleCommand (String userEntry) throws ParseErrorException {
        // Split into commands and details of task
        String[] stringParts = userEntry.split(" ", 2);
        String userCommand = stringParts[0];
        String taskDetails = stringParts.length > 1? stringParts[1] : "";

        // Handle commands
        try {
            switch (userCommand.toLowerCase()) {
            case "list":
                ListTasks listTasks = new ListTasks(taskDetails, taskList);
                listTasks.execute();
                break;
            case "todo":
                AddTodo addTodo = new AddTodo(taskDetails, taskList);
                addTodo.execute();
                break;
            case "deadline":
                AddDeadline addDeadline = new AddDeadline(taskDetails, taskList);
                addDeadline.execute();
                break;
            case "event":
                AddEvent addEvent = new AddEvent(taskDetails, taskList);
                addEvent.execute();
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
            throw new ParseErrorException("\tI'm sorry I don't know what you mean!!\n"
                + "\tPlease choose a valid command :\")");
        } catch (IncompleteCommandException e) {
            String message = e.getMessage();
            throw new ParseErrorException("\t"+message);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            String message = handleBadIndex();
            throw new ParseErrorException(message);
        }
        List<Task> taskListToSave = taskList.getTaskList();
        Storage.updateSaveFile(taskListToSave); // Update file after every command
    }

    /**
     * Prints message asking user for valid task index.
     */
    public String handleBadIndex() {
        if (taskList.isEmpty()) {
            return("\tAdd a task first, then enter a valid task index");
        }
        return("\tEnter a valid task index");
    }

    /**
     * Marks or unmarks the task specified.
     */
    public void handleTaskMarking(String markOrUnmark, String[] stringParts) {
        boolean commandIsMark = markOrUnmark.equals("mark");

        int taskId = Integer.parseInt(stringParts[1])-1;
        Task task = taskList.getTask(taskId);
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
    public void deleteTask(String[] stringParts) {
        int taskId = Integer.parseInt(stringParts[1])-1;
        Task task = taskList.removeTask(taskId);

        System.out.println(LINE);
        System.out.println("\tOk! Deleting this task:");
        System.out.println("\t" + task.getStatusIcon() + task.getTaskType()
                + (taskId + 1) + ". " + task.toString());
        System.out.println(LINE);
    }
}
