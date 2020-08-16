public class Deadline extends Task {
    private final String dateAndTime;
    Deadline (String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    Deadline (String description, boolean isDone, String dateAndTime) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public Deadline markDone() {
        return new Deadline(getDescription(), true, this.dateAndTime);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " +  dateAndTime + ")";
    }
}
