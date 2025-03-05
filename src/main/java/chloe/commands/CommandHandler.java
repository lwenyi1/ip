package chloe.commands;

import chloe.TaskList;
import chloe.exceptions.IncompleteCommandException;

public class CommandHandler {
    protected final String taskDetails;
    protected final TaskList taskList;

    public CommandHandler(String taskDetails, TaskList taskList) {
        this.taskDetails = taskDetails;
        this.taskList = taskList;
    }

    public void execute() throws IncompleteCommandException {

    }
}
