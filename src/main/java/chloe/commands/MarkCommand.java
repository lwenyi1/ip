package chloe.commands;

import chloe.TaskList;
import chloe.tasktypes.Task;

public class MarkCommand extends CommandHandler{
    private final String markOrUnmark;
    private final String[] stringParts;

    public MarkCommand(String markOrUnmark, String[] stringParts, String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
        this.markOrUnmark = markOrUnmark;
        this.stringParts = stringParts;
    }

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
