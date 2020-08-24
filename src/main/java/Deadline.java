public class Deadline extends Task {

    public String dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public Deadline(boolean isDone, String name, String dateTime) {
        super(isDone, name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(), dateTime);
    }
}
