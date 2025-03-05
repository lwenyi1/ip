package chloe.tasktypes;

/**
 * The {@code Deadline} class represents a deadline task in chloe.
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-02-15
 */
public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String taskDescription, String dueDate) {
        super(taskDescription);
        this.dueDate = dueDate;
    }

    /**
     * Prints the addition of a deadline task to the user.
     *
     * This method outputs a decorative line, a message indicating the
     * addition of a deadline task with its description and due date, and
     * another decorative line.
     */
    @Override
    public void printTaskAddition() {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \"" + getTaskDescription() + "\" for you, do it by: "
                + getDueDate() +"!");
        System.out.println(LINE);
    }

    /**
     * Returns the task type of a deadline task.
     *
     * @return The string "[D]" which represents a deadline task.
     */
    @Override
    public String getTaskType() {
        return "[D]";
    }

    /**
     * Retrieves the due date of the deadline task.
     *
     * @return The due date as a string.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string which contains the task description and due date, in the format
     *         "[Done] Task description (by: Due date)"
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Returns a string representation of the deadline task that is suitable for saving to
     * a file.
     *
     * @return A string which contains the task description and due date, in the format
     *         "D # <IsDone> # <TaskDescription> # <DueDate>" where:
     *         - <IsDone> is a 1 if the task is done, or a 0 if it is not.
     *         - <TaskDescription> is the description of the task.
     *         - <DueDate> is the due date of the task.
     */
    @Override
    public String toFileString() {
        return "D # " + (isDone ? "1" : "0") + " # " + taskDescription + " # " + dueDate;
    }
}
