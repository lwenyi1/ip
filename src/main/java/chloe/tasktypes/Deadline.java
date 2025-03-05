package chloe.tasktypes;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dueDate;

    public Deadline(String taskDescription, LocalDateTime dueDate) {
        super(taskDescription);
        this.dueDate = dueDate;
    }

    @Override
    public void printTaskAddition() {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \"" + getTaskDescription() + "\" for you, do it by: "
                + getDueDate() +"!");
        System.out.println(LINE);
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }

    public String getDueDate() {
        return parseDateTimeToString(dueDate);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + parseDateTimeToString(dueDate) + ")";
    }

    @Override
    public String toFileString() {
        return "D # " + (isDone ? "1" : "0") + " # "
                + taskDescription + " # " + parseDateTimeToString(dueDate);
    }
}
