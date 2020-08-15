package main.java;

public class EventTask extends Task {
    public static final String EVENT ="[E]";
    public EventTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return EVENT + super.toString();
    }
}
