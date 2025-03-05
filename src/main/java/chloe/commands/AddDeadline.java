package chloe.commands;

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

        if (taskDescription.isEmpty() || dueDate.isEmpty()) {
            throw new IncompleteCommandException("Please use the format: deadline <description> /by <due date>");
        }

        // Adds deadline to the list
        Deadline newDeadline = new Deadline(taskDescription, dueDate);
        newDeadline.printTaskAddition();
        taskList.addTask(newDeadline);
    }

}
