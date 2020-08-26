public class Deadline extends Task {

    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    @Override
    public String fileText() {
        return "D " + super.fileText() + " | " + this.date;
    }
}