public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(boolean isDone, String description, String date) {
        super(isDone, description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", date);
    }
}
