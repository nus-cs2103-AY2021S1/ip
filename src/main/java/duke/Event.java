package duke;

/**
 * Represents an event inherited from task. A <code>Event</code> object
 * corresponds to a task associated a date as event date
 */

public class Event extends Task {
    /** Constructor.
     *  Initialises message.
     * @param message todo message
     */
    Event(final String message) {
        super(message);
    }

    /** Constructor.
     *  Initialises message, isDone.
     * @param message event message
     * @param isDone event task state
     */
    Event(final String message, final boolean isDone) {
        super(message, isDone);
    }

    /**
     * Returns the single character string denoting task type.
     * @return "E" denoting event
     */
    @Override
    public String getPureTypeLetter() {
        return "E";
    }

    /**
     * Returns the task type notation with square brackets.
     * @return "[E]" denoting event
     */
    @Override
    public String getTypeLetter() {
        return "[E]";
    }

    /**
     * Returns the print message of this event.
     * @return string of form: [E] + [done/undone notation] + message
     */
    @Override
    public String getPrintMessage() {
        return Converter.at(getMessage());
    }

    /**
     * Returns the message of this event
     * @return message
     */
    @Override
    public String getStoreMessage() {
        return getMessage();
    }
}
