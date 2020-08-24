package main.java;

public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getState() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + date + ")";
    }
}
