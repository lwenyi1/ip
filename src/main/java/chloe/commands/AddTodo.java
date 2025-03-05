package chloe.commands;

import chloe.TaskList;
import chloe.exceptions.IncompleteCommandException;
import chloe.tasktypes.Task;

public class AddTodo extends CommandHandler{
    public AddTodo(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

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
