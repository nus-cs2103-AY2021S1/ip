public class Deadline extends Task {

    private static final String MESSAGE_MISSING_DUE = "Did you casually forget to put in the due date of the deadline?";
    private final String dueDateTime;

    public Deadline(String name, String dueDateTime) throws BlankTaskException, MissingDateTimeException {
        super(name);
        if (dueDateTime.isBlank()) {
            throw new MissingDateTimeException(MESSAGE_MISSING_DUE);
        }
        this.dueDateTime = dueDateTime.strip();
    }

    public String getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDateTime + ")";
    }
}
