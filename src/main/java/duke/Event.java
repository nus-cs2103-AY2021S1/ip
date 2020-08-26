package main.java.duke;

import main.java.duke.Task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String[] getInfo() {
        return new String[]{this.getType(), this.isDone(), this.description, this.getAt()};
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
