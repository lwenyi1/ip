package chloe.commands;

import chloe.TaskList;
import chloe.exceptions.IncompleteCommandException;
import chloe.tasktypes.Task;

/**
 * The {@code AddTodo} class is responsible for adding a todo task to the task list.
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class AddTodo extends CommandHandler{
    public AddTodo(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

    /**
     * Adds a todo task to the task list.
     */
    @Override
    public void execute() throws IncompleteCommandException {
        // Adds task to the list
        if (taskDetails.isEmpty()) {
            throw new IncompleteCommandException("Please use the format: todo <description>");
        }

        Task newTask = new Task(taskDetails);
        newTask.printTaskAddition();
        taskList.addTask(newTask);
    }
}
