public class Deadline extends Task {

    private final String dueDateTime;

    public Deadline(String name, String dueDateTime) {
        super(name);
        this.dueDateTime = dueDateTime;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDateTime + ")";
    }
}
