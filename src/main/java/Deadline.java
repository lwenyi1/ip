public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String taskDescription, String dueDate) {
        super(taskDescription);
        this.dueDate = dueDate;
    }

    @Override
    public void printTaskAddition() {
        // Echo the command back to the user
        System.out.println(LINE);
        System.out.println("\tAdded \"" + getTaskDescription() + "\" for you <3, do it by: "
                + getDueDate() +"!!");
        System.out.println(LINE);
    }

    @Override
    public String getTaskType() {
        return "(Deadline)";
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}
