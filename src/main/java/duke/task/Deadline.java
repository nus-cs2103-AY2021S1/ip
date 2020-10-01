package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Task</code> that has to be done by certain date.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Deadline</code>
     * @param deadline the date on which the <code>Deadline</code> is due
     */
    public Deadline(String title, LocalDate deadline) {
        super(title);
        this.deadline = deadline;
    }

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Deadline</code>
     * @param isDone   whether or not the <code>Deadline</code> is marked as completed
     * @param deadline the date on which the <code>Deadline</code> is due
     */
    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    /**
     * Creates a new Deadline from the user's input.
     *
     * @param command the user's input
     * @return the <code>Deadline</code> created
     * @throws InvalidCommandFormatException if the format of the user's input does not follow
     *                                       "deadline [content] /by yyyy-mm-dd"
     */
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

    @Override
    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String print() {
        return "D | " + super.print() + " | " + this.deadline;
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.deadline.equals(date);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline otherTask = (Deadline) obj;
            return super.equals(otherTask) && this.deadline.equals(otherTask.deadline);
        } else {
            return false;
        }
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task == this) {
            return true;
        } else if (task instanceof Deadline) {
            Deadline otherTask = (Deadline) task;
            return super.isDuplicate(otherTask);
        } else {
            return false;
        }
    }
}
