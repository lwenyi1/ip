package chloe;

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
}
