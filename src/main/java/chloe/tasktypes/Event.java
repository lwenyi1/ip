package chloe.tasktypes;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String taskDescription, String from, String to) {
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
}
