package main.java;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public void printDescription() {
        System.out.println("[D][" + getStatusIcon()
                + "]" + description + "(by:" + deadline + ")");
    }
}
