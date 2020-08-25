package main.java;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String[] getInfo() {
        return new String[]{this.getType(), this.isDone(), this.description, this.getBy()};
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
