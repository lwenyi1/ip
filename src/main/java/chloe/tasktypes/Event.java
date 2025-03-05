package chloe.tasktypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String taskDescription, LocalDateTime from, LocalDateTime to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    @Override
    public void printTaskAddition() {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \"" + getTaskDescription()
                + "\", happening on " + from + " to " + to + "!");
        System.out.println(LINE);
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileString() {
        return "E # " + (isDone ? "1" : "0") + " # " + taskDescription + " # " + from + " # " + to;
    }
}
