package chloe.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chloe.TaskList;
import chloe.exceptions.IncompleteCommandException;

public class CommandHandler {
    protected static final String LINE = "\t**********************************************";
    // TODO remove these so not every child class has to define them
    protected final String taskDetails;
    protected final TaskList taskList;

    public CommandHandler(String taskDetails, TaskList taskList) {
        this.taskDetails = taskDetails;
        this.taskList = taskList;
    }

    public void execute() throws IncompleteCommandException {

    }

    protected LocalDateTime parseStringToDateTime(String dateTimeString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTimeString, inputFormatter);
    }
}
