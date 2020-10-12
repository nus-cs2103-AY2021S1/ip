package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Task</code> that takes place on a certain date.
 */
public class Event extends Task {
    private LocalDate duration;

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Event</code>
     * @param duration the date on which the <code>Event</code> takes place
     */
    public Event(String title, LocalDate duration) {
        super(title);
        this.duration = duration;
    }

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Event</code>
     * @param isDone   whether or not the <code>Event</code> is marked as completed
     * @param duration the date on which the <code>Event</code> takes place
     */
    public Event(String title, boolean isDone, LocalDate duration) {
        super(title, isDone);
        this.duration = duration;
    }

    /**
     * Creates a new Event from the user's input.
     *
     * @param command the user's input
     * @return the <code>Event</code> created
     * @throws InvalidCommandFormatException if the format of the user's input does not follow
     *                                       "event [content] /at yyyy-mm-dd"
     */
    public static Event of(String command) throws InvalidCommandFormatException {
        if (command.length() <= 6) {
            throw new InvalidCommandFormatException("Event cannot be empty.");
        }
        String[] split = command.substring(6).trim().split("\\s+/at\\s+");
        if (split.length != 2) {
            throw new InvalidCommandFormatException("Wrong format for event command.");
        }
        try {
            return new Event(split[0], LocalDate.parse(split[1]));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Please enter a valid date in the yyyy-mm-dd format.");
        }
    }

    @Override
    public String toString() {
        String date = this.duration.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    @Override
    public String print() {
        return "E | " + super.print() + " | " + this.duration;
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.duration.equals(date);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event) {
            Event otherTask = (Event) obj;
            return super.equals(otherTask) && this.duration.equals(otherTask.duration);
        } else {
            return false;
        }
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task == this) {
            return true;
        } else if (task instanceof Event) {
            Event otherTask = (Event) task;
            return super.isDuplicate(otherTask);
        } else {
            return false;
        }
    }
}
