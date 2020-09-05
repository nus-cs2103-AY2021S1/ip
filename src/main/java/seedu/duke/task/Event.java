package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Task</code> that takes place on a certain date.
 */
public class Event extends Task {
    private LocalDate duration;

    public Event(String title, LocalDate duration) {
        super(title);
        this.duration = duration;
    }

    public Event(String title, boolean isDone, LocalDate duration) {
        super(title, isDone);
        this.duration = duration;
    }

    public static Event of(String command) throws InvalidCommandFormatException {
        if (command.length() <= 6) {
            throw new InvalidCommandFormatException("Event cannot be empty.");
        }
        String[] split = command.substring(6).split("\\s+/at\\s+");
        if (split.length != 2) {
            throw new InvalidCommandFormatException("Wrong format for event command.");
        }
        try {
            return new Event(split[0], LocalDate.parse(split[1]));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Please enter a valid date in the yyyy-mm-dd format.");
        }
    }

    public String toString() {
        String date = this.duration.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    public String print() {
        return "E | " + super.print() + " | " + this.duration;
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.duration.equals(date);
    }
}
