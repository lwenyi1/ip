public class Task {
    protected String taskDescription;
    protected boolean isDone;
    private static final String LINE = "\t**********************************************";

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;

        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \""+taskDescription+"\" for youu <3");
        System.out.println(LINE);
    }

    public String getTaskType() {
        return "[T]"; // T for Task
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    public void updateStatus(boolean isMarked) {
        isDone = isMarked;
    }
}
