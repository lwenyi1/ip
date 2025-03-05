package chloe.commands;

import chloe.TaskList;
import chloe.tasktypes.Task;

/**
 * The {@code DeleteTask} class is responsible for deleting a task from the task list.
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class DeleteTask extends CommandHandler{
    String[] stringParts;
    public DeleteTask(String[] stringParts, String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
        this.stringParts = stringParts;
    }

    /**
     * Executes the delete task command, which removes a task from the task list by
     * its ID.
     *
     * <p>
     * This method retrieves the task ID from the command input, removes the
     * corresponding task from the task list, and prints a confirmation message with the details
     * of the deleted task.
     * </p>
     */

    @Override
    public void execute() {
        int taskId = Integer.parseInt(stringParts[1]) - 1;
        Task task = taskList.removeTask(taskId);

        System.out.println(LINE);
        System.out.println("\tOk! Deleting this task:");
        System.out.println("\t" + task.getStatusIcon() + task.getTaskType()
                + (taskId + 1) + ". " + task.toString());
        System.out.println(LINE);
    }
}
