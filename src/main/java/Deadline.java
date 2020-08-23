public class Deadline extends Task {

    protected String date;

    public Deadline (String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline (String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String toStoredTextString() {
        return "D | " + super.toStoredTextString() + " | " + date;
    }
}
