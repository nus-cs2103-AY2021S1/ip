package duke.task;

import duke.utils.Datetime;

import java.util.Optional;
import java.time.LocalDateTime;

public class Event extends Task{
    private final Datetime time;

    public static final String EVENT_SYMBOL = "E";
    public static final String TIME_FORMAT_INPUT = "HHmm";
    public static final String TIME_FORMAT_OUTPUT = "hh:mm a";
    public static final String EVENT_BREAK = "/at";
    public static final int COMMAND_LENGTH = 2;

    public Event(String description, boolean isCompleted, LocalDateTime time) {
        super(description, isCompleted);
        this.time = new Datetime(time, TIME_FORMAT_INPUT, TIME_FORMAT_OUTPUT);
    }

    public static Event createEvent(String description, LocalDateTime time) {
        return new Event(description, false, time);
    }

    @Override
    public String toString() {
        String stringTime = this.time.getOutputDatetimeString();
        String atTime = String.format("(at: %s)", stringTime);
        return "[" + EVENT_SYMBOL + "]" + toStringSuffix() + " " + atTime;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.isEqual(otherEvent);
        }
        return false;
    }

    @Override
    public String getTaskSymbol() {
        return EVENT_SYMBOL;
    }

    @Override
    public Optional<String> getTaskDatetime() {
        String stringTime = this.time.getOutputDatetimeString();
        return Optional.of(stringTime);
    }
}
