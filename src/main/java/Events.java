package main.java;

public class Events extends Task {

    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + at + ")";
    }
}
