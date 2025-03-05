package chloe.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chloe.TaskList;
import chloe.exceptions.IncompleteCommandException;
import chloe.tasktypes.Deadline;

/**
 * The {@code AddDeadline} class is responsible for adding a deadline task to the task list.
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class AddDeadline extends CommandHandler{
    public AddDeadline(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

    @Override
    public void execute() throws IncompleteCommandException {
        // Split task name and due date
        String[] stringParts = taskDetails.split(" /by ", 2);
        String taskDescription = stringParts[0];
        String dueDate = stringParts.length > 1? stringParts[1] : "";
        LocalDateTime parsedDueDate;

        if (taskDescription.isEmpty() || dueDate.isEmpty()) {
            throw new IncompleteCommandException("Please use the format: deadline <description> /by <due date>");
        }

        try {
            parsedDueDate = parseStringToDateTime(dueDate);
        } catch (DateTimeParseException e) {
            throw new IncompleteCommandException("Please date and time in the format dd/MM/yyyy HH:mm");
        }

        // Adds deadline to the list
        Deadline newDeadline = new Deadline(taskDescription, parsedDueDate);
        newDeadline.printTaskAddition();
        taskList.addTask(newDeadline);
    }

}
