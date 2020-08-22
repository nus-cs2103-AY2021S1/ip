public class Deadline extends Task {
    private String datetime;

    public Deadline(String description, String date) {
        super(description);
        this.datetime = date;
    }

    @Override
    public String textFormat() {
        return "deadline, " + super.textFormat() + "/by" + this.datetime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.datetime + ")";
    }
}
