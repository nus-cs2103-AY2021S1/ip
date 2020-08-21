package main.java;

public class Event extends Task{
    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String record() {
        if(this.isCompleted) {
            return "E | 1 | " + description + " | " + at;
        } else {
            return "E | 0 | " + description + " | " + at;
        }
    }
}
