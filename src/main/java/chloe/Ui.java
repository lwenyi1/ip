package chloe;

import java.util.List;
import java.util.Scanner;

import chloe.exceptions.IllegalCommandException;
import chloe.exceptions.IncompleteCommandException;
import chloe.exceptions.ParseErrorException;

import chloe.commands.CommandHandler;
import chloe.tasktypes.Task;

/**
 * The {@code Ui} class is responsible for handling user input and displaying the program's output.
 * 
 * <p>This class provides methods to greet the user, respond to commands, and display the program's output.</p>
 *
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class Ui {
    // Line used when printing replies
    private static final String LINE = "\t**********************************************";
    static Parser parser = new Parser();

    static TaskList taskList;

    public Ui() {
        taskList = new TaskList();
        Storage.loadSavedTasks(taskList);
    }

    /**
     * Runs the interactive command-line interface for Chloe.
     *
     * <p>This method greets the user and enters an interactive loop that reads user input
     * commands, parses them, and executes the corresponding actions. The loop continues
     * until the user inputs "bye". It handles various exceptions, providing feedback for
     * invalid commands and input errors. The task list is updated and saved to file after
     * every valid command execution.</p>
     */

    public static void run() {
        sayHi(); // Greet the user
        Scanner scanner = new Scanner(System.in); // initiate scanner
        CommandHandler commandHandler;

        // Interactive loop
        while (true) {
            // Read user entries
            if (!scanner.hasNextLine()) { // Avoid reading if no input is available
                break;
            }
            String userEntry = scanner.nextLine();
            if (userEntry.equals("bye")) {
                break;
            }
            try {
                commandHandler = parser.parseCommand(userEntry, taskList);
                commandHandler.execute();
            } catch (IllegalCommandException e) {
                handleException(e.getMessage());
            } catch (IncompleteCommandException e) {
                handleException("\t"+e.getMessage());
            } catch ( NumberFormatException | IndexOutOfBoundsException e) {
                String message = handleBadIndex();
                handleException(message);
            }

            List<Task> taskListToSave = taskList.getTaskList();
            Storage.updateSaveFile(taskListToSave); // Update file after every command
        }

        scanner.close();
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

    /**
     * Handles bad indexes, returning the appropriate string.
     *
     * @return message
     */
    public static String handleBadIndex() {
        if (taskList.getListSize() == 0) {
            return "\tAdd a task first, then enter a valid task index";
        }
        return "\tEnter a valid task index";
    }

    /**
     * Handles exception by printing messages.
     * 
     * @param message
     */
    public static void handleException(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}
