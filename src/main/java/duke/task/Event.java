package duke.task;

import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task{
    private final Date time;

    public static final String EVENT_SYMBOL = "E";
    public static final SimpleDateFormat TIME_FORMAT_INPUT = new SimpleDateFormat("HHmm");
    public static final SimpleDateFormat TIME_FORMAT_OUTPUT = new SimpleDateFormat("hh:mm a");
    public static final String EVENT_BREAK = "/at";
    public static final int COMMAND_LENGTH = 2;

    public Event(String description, boolean isCompleted, Date time) {
        super(description, isCompleted);
        this.time = time;
    }

    public static Event createEvent(String description, Date time) {
        return new Event(description, false, time);
    }

    @Override
    public String toString() {
        String stringTime = TIME_FORMAT_OUTPUT.format(this.time);
        String atTime = String.format("(at: %s)", stringTime);
        return "[" + EVENT_SYMBOL + "]" + toStringSuffix() + " " + atTime;
    }

    @Override
    public String getTaskSymbol() {
        return EVENT_SYMBOL;
    }

    @Override
    public Optional<String> getTaskDatetime() {
        String stringTime = TIME_FORMAT_OUTPUT.format(this.time);
        return Optional.of(stringTime);
    }
}
