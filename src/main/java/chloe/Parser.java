package chloe;

// For chloe task management
import chloe.commands.CommandHandler;
import chloe.commands.AddDeadline;
import chloe.commands.AddEvent;
import chloe.commands.AddTodo;
import chloe.commands.DeleteTask;
import chloe.commands.FindTask;
import chloe.commands.ListTasks;
import chloe.commands.MarkCommand;
import chloe.exceptions.IllegalCommandException;

public class Parser {

    public CommandHandler parseCommand (String userEntry, TaskList taskList) throws IllegalCommandException {
        // Split into commands and details of task
        String[] stringParts = userEntry.split(" ", 2);
        String userCommand = stringParts[0];
        String taskDetails = stringParts.length > 1? stringParts[1] : "";

        String ILLEGAL_COMMAND_MESSAGE = "\tI'm sorry I don't know what you mean!!\n" +
                "\tPlease choose a valid command :\")";

        // Handle commands
        return switch (userCommand.toLowerCase()) {
            case "list" -> new ListTasks(taskDetails, taskList);
            case "todo" -> new AddTodo(taskDetails, taskList);
            case "deadline" -> new AddDeadline(taskDetails, taskList);
            case "event" -> new AddEvent(taskDetails, taskList);
            case "mark", "unmark" -> new MarkCommand(userCommand.toLowerCase(), stringParts, taskDetails, taskList);
            case "find" -> new FindTask(taskDetails, taskList);
            case "delete" -> new DeleteTask(stringParts, taskDetails, taskList);
            default -> throw new IllegalCommandException(ILLEGAL_COMMAND_MESSAGE);
        };
    }
}
