package chloe.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chloe.TaskList;
import chloe.exceptions.IncompleteCommandException;
import chloe.tasktypes.Event;

public class AddEvent extends CommandHandler{
    public AddEvent(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

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
