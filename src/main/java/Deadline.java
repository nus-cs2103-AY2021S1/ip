public class Deadline extends Task {
    private String timeDescription;

    public Deadline(String description, String timeDescription) {
        super(description, "D");
        this.timeDescription = timeDescription;
    }

    public Deadline(String description, String timeDescription, boolean isDone) {
        super(description, "D", isDone);
        this.timeDescription = timeDescription;
    }

    public String getTimeDescription() {
        return timeDescription;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description
                + "(by:" + this.timeDescription + ")";
    }
}
