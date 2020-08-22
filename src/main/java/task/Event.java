package task;

import java.util.Optional;

public class Event extends Task{
    private final String time;

    public static final String EVENT_SYMBOL = "E";
    public static final String EVENT_BREAK = "/at";
    public static final int COMMAND_LENGTH = 2;

    public Event(String description, boolean completed, String time) {
        super(description, completed);
        this.time = time;
    }

    public static Event createEvent(String description, String time) {
        return new Event(description, false, time);
    }

    @Override
    public String toString() {
        String atTime = String.format("(at: %s)", this.time);
        return "[" + EVENT_SYMBOL + "]" + toStringSuffix() + " " + atTime;
    }

    @Override
    public String getTaskSymbol() {
        return EVENT_SYMBOL;
    }

    @Override
    public Optional<String> getTaskDatetime() {
        return Optional.of(this.time);
    }
}
