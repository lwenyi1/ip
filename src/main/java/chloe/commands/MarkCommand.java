package chloe.commands;

import chloe.TaskList;
import chloe.tasktypes.Task;

/**
 * The {@code MarkCommand} class is responsible for marking or unmarking a task in the task list.
 *
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class MarkCommand extends CommandHandler{
    private final String markOrUnmark;
    private final String[] stringParts;

    public MarkCommand(String markOrUnmark, String[] stringParts, String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
        this.markOrUnmark = markOrUnmark;
        this.stringParts = stringParts;
    }

    /**
     * Marks or unmarks a task in the task list.
     *
     * This method extracts the task id from the user's input and marks or unmarks the task with the given id.
     * It then prints out the updated task status and description.
     */
    @Override
    public void execute() {
        boolean commandIsMark = markOrUnmark.equals("mark");

        int taskId = Integer.parseInt(stringParts[1])-1;
        Task task = taskList.getTask(taskId);
        task.updateStatus(commandIsMark);

        System.out.println(LINE);
        if (commandIsMark) {
            System.out.println("\tMarked \"" + task.getTaskDescription()
                    + "\" as done for you!");
        } else {
            System.out.println("\tOh \"" + task.getTaskDescription()
                    + "\" is not done? Unmarked.");
        }
        System.out.println("\t" + task.getStatusIcon() + task.getTaskType()
                + (taskId + 1) + ". " + task.toString());
        System.out.println(LINE);
    }
}
