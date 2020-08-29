package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exceptions.WrongDateTimeFormatException;

public class Deadline extends Task {

    /**
     * The deadline of the task
     **/
    private final LocalDateTime deadline;

    /**
     * Initializes Deadline task
     *
     * @param name
     * @param isDone
     * @param end
     * @throws WrongDateTimeFormatException
     */
    public Deadline(String name, boolean isDone, String end) throws WrongDateTimeFormatException {
        super(name, isDone);
        try {
            this.deadline = LocalDate.parse(end.substring(0, 10)).atTime(
                Integer.parseInt(end.substring(11, 13)),
                Integer.parseInt(end.substring(13))
            );
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException(
                "☹ OOPS!!! Please enter the deadline time in the right format. (YYYY-MM-DD HHmm)");
        }
    }

    /**
     * Initializes deadline task
     *
     * @param name
     * @param isDone
     * @param deadline
     */
    public Deadline(String name, boolean isDone, LocalDateTime deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * Set the current task to done
     **/
    @Override
    public Task setToTrue() {
        return new Deadline(this.name, true, this.deadline);
    }

    /**
     * Get the type of the current task
     **/
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Get the deadline of the current task
     **/
    @Override
    public String getEnd() {
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Convert the task to string
     **/
    @Override
    public String toString() {
        return isDone
            ? "[D][✓] " + this.getName() + " (by: "
            + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")"
            : "[D][✗] " + this.getName() + " (by: "
            + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    /**
     * Override the equals from Object so that it can be used to handle deadline
     **/
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline temp = (Deadline) o;
            return this.name.equals(temp.name) && (this.isDone == temp.isDone) && this.deadline.equals(temp.deadline);
        } else {
            return false;
        }
    }
}
