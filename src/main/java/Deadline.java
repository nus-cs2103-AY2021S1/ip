public class Deadline extends Task {
    private static final String STRING_FORMAT = "[D][%s] %s (by: %s)";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s|%s";

    protected String dueDate;

    public Deadline(String description, boolean isDone, String dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toTaskData() {
        return String.format(Deadline.TASK_DATA_FORMAT, "D", isDone ? 1 : 0, description, dueDate);
    }

    @Override
    public String toString() {
        return String.format(Deadline.STRING_FORMAT, getStatusIcon(), description, dueDate);
    }
}
