package duke;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, Boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description + " (by:" + this.deadline + ")";
    }

    @Override
    public Deadline markAsDone() {
        //int index = taskNum - 1;
        Deadline newTask = new Deadline(this.getDescription(), true, this.deadline);
        return newTask;
    }

    public String getDate() {
        return this.deadline;
    }
}
