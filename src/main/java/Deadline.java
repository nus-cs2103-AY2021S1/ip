public class Deadline extends Task {
    private static final String STRING_FORMAT = "[D][%s] %s (by: %s)";

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format(Deadline.STRING_FORMAT, getStatusIcon(), description, dueDate);
    }
}
