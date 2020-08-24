package main.java;

public class Event extends Task {
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + " | " + time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
