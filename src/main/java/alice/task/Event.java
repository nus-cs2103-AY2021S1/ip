package alice.task;

import alice.AliceException;
import alice.task.time.TaskDateTime;

/**
 * Represents an event.
 */
public class Event extends Task {
    private final TaskDateTime on;

    /**
     * Creates a undone event happening at the specified time.
     *
     * @param description describes the event.
     * @param on          the date and time of the event.
     */
    public Event(String description, TaskDateTime on) {
        this(false, description, on);
    }

    /**
     * Creates an event happening at the specified time.
     *
     * @param isDone      the completion status of the event, true if completed; false otherwise.
     * @param description describes the event.
     * @param on          the date and time of the event.
     */
    public Event(boolean isDone, String description, TaskDateTime on) {
        super(isDone, description);
        this.on = on;

        assert !description.isBlank() : "Cannot create an Event with no description";
    }

    /**
     * Decode an encoded string representation of the event.
     *
     * @param saved the string representation of the encoded event.
     * @return the {@code Event} described in the string representation.
     * @throws AliceException if the encoded string is corrupted.
     */
    public static Event decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Event(isDone, inputs[1], TaskDateTime.decode(inputs[2]));
        } else {
            throw new AliceException("Corrupted event data");
        }
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + " | " + on.encode();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + on + ")";
    }
}
