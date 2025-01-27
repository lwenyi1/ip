public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
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
