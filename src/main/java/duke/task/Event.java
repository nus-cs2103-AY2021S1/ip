package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event to be done at a specified date.
 */
public class Event extends Task {
    /** The event date of the task. */
    protected LocalDate at;

    /**
     * Creates a new Event with the given description and date.
     *
     * @param description is the description of the event.
     * @param at is the event date.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Gives an Event task from the full task description in the file.
     *
     * @param loadTask is the full task description in the file.
     * @return an Event task.
     */
    public static Event load(String loadTask) {
        String[] splitTask = loadTask.split(" \\| ", 4);
        Event event = new Event(splitTask[2], LocalDate.parse(splitTask[3]));
        if (splitTask[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Gives a string indicating whether the event is done and the description of the event.
     *
     * @param isFinished is the value 1 if event is done or 0 if event is not done.
     * @return a string indicating 1 or 0 and the description of the event.
     */
    @Override
    public String save(int isFinished) {
        return "E | " + super.save(isFinished) + " | " + this.at;
    }

    /**
     * Checks if this event is equal to another obj.
     *
     * @param obj is the obj to check.
     * @return true if this is equals to the other obj.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.description.equals(event.description)
                    && this.at.equals(event.at);
        } else {
            return false;
        }
    }
    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
