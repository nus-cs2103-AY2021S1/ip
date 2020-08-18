public class Deadline extends Task {

    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }
}
