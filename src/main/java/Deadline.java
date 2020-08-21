public class Deadline extends Task {
    private static final String STRING_FORMAT = "[D][%s] %s (by: %s)";

    protected String dueDate;

    public Deadline(String description, boolean isDone, String dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format(Deadline.STRING_FORMAT, getStatusIcon(), description, dueDate);
    }
}
