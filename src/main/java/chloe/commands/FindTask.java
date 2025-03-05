package chloe.commands;

import java.util.ArrayList;
import java.util.List;

import chloe.TaskList;
import chloe.tasktypes.Task;

public class FindTask extends CommandHandler{
    List<Task> relatedTasks = new ArrayList<Task>();

    public FindTask(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

    @Override
    public void execute() {
        for (Task task : taskList.getTaskList()) {
            if (task.getTaskDescription().contains(taskDetails)) {
                relatedTasks.add(task);
            }
        }

        System.out.println(LINE);
        System.out.println("\tLocated " + relatedTasks.size() + " matching things in your list:");
        for(int i = 0; i < relatedTasks.size(); i++) {
            Task task = relatedTasks.get(i);
            System.out.println("\t"+task.getStatusIcon() + task.getTaskType()
                    +(i+1)+". " + task.toString());
        }
        System.out.println(LINE);
    }
}
