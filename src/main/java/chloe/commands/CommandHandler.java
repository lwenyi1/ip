package chloe.commands;

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
}
