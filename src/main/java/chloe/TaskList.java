package chloe;

import java.util.ArrayList;
import java.util.List;

import chloe.tasktypes.Task;

public class TaskList {
    private List<Task> taskList = new ArrayList<>(); // List of tasks

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getListSize() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Removes tasks from the task list.
     *
     * @param taskId the numerical ID of the task to be removed.
     * @return Task that was removed.
    */
    public Task removeTask(int taskId) {
        Task task = taskList.get(taskId);
        taskList.remove(taskId);
        return task;
    }
}
