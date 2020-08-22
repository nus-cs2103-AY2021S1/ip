//  Deadlines: tasks that need to be done before a specific date/time
//  e.g., submit report by 11/10/2019 5pm

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String fileFormat() {
        return String.format("%1$s|%2$s|%3$s|%4$s", "D", this.isDone ? "0" : "1", this.description, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%1$s (by: %2$s)", super.toString(), this.deadline);
    }
}