package chloe.tasktypes;

/**
 * The {@code Task} class represents a task in chloe.
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-02-15
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected static final String LINE = "\t**********************************************";

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;

    }

    /**
     * Prints the addition of a task to the user.
     *
     * This method outputs a decorative line, a message indicating the
     * addition of a task with its description, and another decorative line.
     */
    public void printTaskAddition() {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \""+getTaskDescription()+"\" for you!");
        System.out.println(LINE);
    }

    /**
     * Returns the task type of a task.
     *
     * @return The string "[T]" which represents a task.
     */
    public String getTaskType() {
        return "[T]"; // Default type
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return A string that is "[X]" if the task is done, or "[ ]" if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Updates the status of the task to done or not done.
     *
     * @param isMarked true if the task is done, false if it is not.
     */
    public void updateStatus(boolean isMarked) {
        isDone = isMarked;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string which contains the task description.
     */
    public String toString() {
        return taskDescription;
    }

    /**
     * Returns a string representation of the task that is suitable for saving to
     * a file.
     *
     * @return A string which contains the task description and status, in the
     *         format "T # <IsDone> # <TaskDescription>" where:
     *         - <IsDone> is a 1 if the task is done, or a 0 if it is not.
     *         - <TaskDescription> is the description of the task.
     */
    public String toFileString() {
        return "T # " + (isDone ? "1" : "0") + " # " + taskDescription;
    }
}
