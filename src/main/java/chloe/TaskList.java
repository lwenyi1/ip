package chloe;

import java.util.ArrayList;
import java.util.List;

import chloe.tasktypes.Task;

/**
 * The {@code TaskList} class represents a list of tasks.
 * 
 * <p>This class provides methods to add, get, and remove tasks from the list.</p>
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>(); // List of tasks

    /**
     * Adds a task to the task list.
     * 
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= getListSize()).
     */

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return A list containing all tasks.
     */

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getListSize() {
        return taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
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
