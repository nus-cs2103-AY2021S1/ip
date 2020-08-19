package main.java;

public class DeadLine extends Task {
    private String deadLine;

    public DeadLine(String description, String deadLine) {
        super(description);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadLine + ")";
    }
}
