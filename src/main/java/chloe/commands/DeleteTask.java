package chloe.commands;

import chloe.TaskList;
import chloe.tasktypes.Task;

public class DeleteTask extends CommandHandler{
    String[] stringParts;
    public DeleteTask(String[] stringParts, String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
        this.stringParts = stringParts;
    }

    @Override
    public void execute() {
        int taskId = Integer.parseInt(stringParts[1])-1;
        Task task = taskList.removeTask(taskId);

        System.out.println(LINE);
        System.out.println("\tOk! Deleting this task:");
        System.out.println("\t" + task.getStatusIcon() + task.getTaskType()
                + (taskId + 1) + ". " + task.toString());
        System.out.println(LINE);
    }
}
