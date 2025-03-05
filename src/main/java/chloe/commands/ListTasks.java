package chloe.commands;

import chloe.TaskList;
import chloe.tasktypes.Task;

public class ListTasks extends CommandHandler{

    public ListTasks(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

    @Override
    public void execute() {
        System.out.println(LINE);
        System.out.println("\tyou have " + taskList.getListSize() + " things in your list:");
        for(int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            System.out.println("\t"+task.getStatusIcon() + task.getTaskType()
                    +(i+1)+". " + task.toString());
        }
        System.out.println(LINE);
    }
}
