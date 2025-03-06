package chloe.tasktypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents an event task in chloe.
 * 
 * @author Wenyi
 * @version 1.0
 * @since 2025-02-15
 */
public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String taskDescription, LocalDateTime from, LocalDateTime to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    /**
     * Prints the addition of an event task to the user.
     *
     * This method outputs a decorative line, a message indicating the
     * addition of an event task with its description, start date/time, and
     * end date/time, and another decorative line.
     */
    @Override
    public void printTaskAddition() {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \"" + getTaskDescription()
                + "\", happening on " + from + " to " + to + "!");
        System.out.println(LINE);
    }

    /**
     * Returns the task type of an event task.
     *
     * @return The string "[E]" which represents an event task.
     */
    @Override
    public String getTaskType() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string which contains the task description, start date/time, and
     *         end date/time, in the format "[Done] Task description (from: Start date/time to: End date/time)"
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + parseDateTimeToString(this.from)
                + " to: " + parseDateTimeToString(this.to) + ")";
    }

    /**
     * Returns a string representation of the event task that is suitable for saving to
     * a file.
     *
     * @return A string which contains the task description, start date/time, and
     *         end date/time, in the format
     *         "E # <IsDone> # <TaskDescription> # <Start date/time> # <End date/time>" where:
     *         - <IsDone> is a 1 if the task is done, or a 0 if it is not.
     *         - <TaskDescription> is the description of the task.
     *         - <Start date/time> is the start date/time of the event.
     *         - <End date/time> is the end date/time of the event.
     */
    @Override
    public String toFileString() {
        return "E # " + (isDone ? "1" : "0") + " # " + taskDescription
                + " # " + parseDateTimeToString(from) + " # " + parseDateTimeToString(to);
    }
}
