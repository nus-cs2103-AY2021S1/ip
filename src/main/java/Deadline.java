package main.java;

public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " ----- By: " + this.date;
    }
}
