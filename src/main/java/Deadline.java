public class Deadline extends Task {
    private final String timeDescription;

    Deadline(String taskDescription, String timeDescription) {
        super(taskDescription);
        this.timeDescription = timeDescription;
    }

    public String getTimeDescription() {
        return timeDescription;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.timeDescription + ")";
    }
}
