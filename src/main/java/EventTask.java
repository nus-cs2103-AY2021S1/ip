package main.java;

public class EventTask extends Task {
    public static final String EVENT ="[E]";
    public EventTask(String description) {
        super(description);
    }

    public EventTask() {}

    public String getType() {
        return EVENT;
    }

    @Override
    public String toString() {
        return EVENT + super.toString();
    }
}
