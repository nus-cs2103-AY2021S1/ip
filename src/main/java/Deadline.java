public class Deadline extends Task {
    String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    Deadline(String description, String date, boolean done) {
        super(description, done);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
