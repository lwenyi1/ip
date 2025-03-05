package chloe.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chloe.TaskList;
import chloe.exceptions.IncompleteCommandException;
import chloe.tasktypes.Event;

/**
 * The {@code AddEvent} class is responsible for adding an event task to the task list.
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class AddEvent extends CommandHandler{
    public AddEvent(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

    /**
     * Executes the command to add an event task to the task list.
     *
     * <p>This method processes the task details to split the task description, start date/time,
     * and end date/time. It throws an {@code IncompleteCommandException} if the task description,
     * start date/time, or end date/time is missing. Otherwise, it creates a new {@code Event}
     * object, prints a message indicating the addition of the event, and adds it to the task list.</p>
     *
     * @throws IncompleteCommandException If the task description, start date/time, or end date/time is missing.
     */

    @Override
    public void execute() throws IncompleteCommandException {
        // Split task name and due date
        String[] stringParts = taskDetails.split(" /from ", 2);
        String taskDescription = stringParts[0];
        String[] timeParts =
                (stringParts.length > 1? stringParts[1] : "").split(" /to ");
        String from = timeParts[0];
        String to = timeParts.length > 1? timeParts[1] : "";

        LocalDateTime parsedStartDate;
        LocalDateTime parsedEndDate;

        if (taskDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IncompleteCommandException("Please use the format: event <description> /from <date/time>"
                    + " /to <end date/time>");
        }

        try {
            parsedStartDate = parseStringToDateTime(from);
            parsedEndDate = parseStringToDateTime(to);
        } catch (DateTimeParseException e) {
            throw new IncompleteCommandException("Please date and time in the format dd/MM/yyyy HH:mm");
        }

        // Adds event to the list
        Event newEvent = new Event(taskDescription, parsedStartDate, parsedEndDate);
        newEvent.printTaskAddition();
        taskList.addTask(newEvent);
    }
}
