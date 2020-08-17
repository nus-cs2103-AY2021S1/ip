public class Deadline extends Task {
    protected String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
