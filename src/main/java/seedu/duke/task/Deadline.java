package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Task</code> that has to be done by certain date.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String title, LocalDate deadline) {
        super(title);
        this.deadline = deadline;
    }

    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    public static Deadline of(String command) throws InvalidCommandFormatException {
        if (command.length() <= 9) {
            throw new InvalidCommandFormatException("Deadline cannot be empty.");
        }
        String[] split = command.substring(9).split("\\s+/by\\s+");
        if (split.length != 2) {
            throw new InvalidCommandFormatException("Wrong format for deadline command.");
        }
        try {
            return new Deadline(split[0], LocalDate.parse(split[1]));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Please enter a valid date in the yyyy-mm-dd format.");
        }

    }

    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String print() {
        return "D | " + super.print() + " | " + this.deadline;
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.deadline.equals(date);
    }
}
