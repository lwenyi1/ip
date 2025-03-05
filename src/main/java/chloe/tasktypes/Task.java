package chloe.tasktypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chloe.exceptions.IncompleteCommandException;

public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected static final String LINE = "\t**********************************************";

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;

    }

    public void printTaskAddition() {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \""+getTaskDescription()+"\" for you!");
        System.out.println(LINE);
    }

    public String getTaskType() {
        return "[T]"; // Default type
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void updateStatus(boolean isMarked) {
        isDone = isMarked;
    }

    public String toString() {
        return taskDescription;
    }

    public String toFileString() {
        return "T # " + (isDone ? "1" : "0") + " # " + taskDescription;
    }



    protected String parseDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(outputFormatter);
    }
}
