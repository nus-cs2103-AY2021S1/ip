package duke;

/**
 * Represents an event inherited from task. A <code>Event</code> object corresponds to
 * a task associated a date as event date
 */

public class Event extends Task {
    Event(String message) {
        super(message);
    }

    Event(String message, boolean isDone) {
        super(message, isDone);
    }
    @Override
    public String getPureTypeLetter() {
        return "E";
    }

    @Override
    public String getTypeLetter() {
        return "[E]";
    }

    @Override
    public String getPrintMessage() {
        return Converter.at(getMessage());
    }

    @Override
    public String getStoreMessage() {
        return getMessage();
    }
}
