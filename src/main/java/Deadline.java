public class Deadline extends TimedTask {

    public Deadline(String description, String datetime) {
        super(description, datetime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.datetimeString() + ")";
    }
}
