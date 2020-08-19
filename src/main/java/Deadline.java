public class Deadline extends Task {
    private String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + time + ")";
    }
}
