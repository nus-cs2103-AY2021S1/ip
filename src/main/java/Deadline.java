public class Deadline  extends Task {
    private String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date + ")";
    }
}
