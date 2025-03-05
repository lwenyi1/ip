package chloe.commands;

import java.util.ArrayList;
import java.util.List;

import chloe.TaskList;
import chloe.tasktypes.Task;

/**
 * The {@code FindTask} class is responsible for searching tasks in a task list
 * based on a given keyword. It extends the {@code CommandHandler} class.
 *
 * <p>This class stores matching tasks in a list and prints them when executed.</p>
 */
public class FindTask extends CommandHandler{
    List<Task> relatedTasks = new ArrayList<Task>();

    /**
     * Constructs a {@code FindTask} object with a search keyword and task list.
     *
     * @param taskDetails The search keyword to find in task descriptions.
     * @param taskList The {@code TaskList} containing all tasks.
     */
    public FindTask(String taskDetails, TaskList taskList) {
        super(taskDetails, taskList);
    }

    /**
     * Executes the task search operation.
     *
     * <p>This method iterates through the task list and finds tasks whose
     * descriptions contain the given keyword. The results are printed
     * in a formatted list.</p>
     *
     * <p>The output includes the number of matching tasks and their details.</p>
     */
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
