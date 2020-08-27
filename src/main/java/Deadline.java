import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that has a specific deadline.
 * A Deadline object is made up of the task name, as well as a date and time of the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private String originalFormat;
    private String printedFormat;

    /**
     * Instantiates a Deadline object if the input was written in the correct format.
     *
     * @param taskName The description of the task.
     * @param date The date and time of the deadline.
     * @throws DukeException If the deadline is not specified in the correct format.
     */
    public Deadline(String taskName, String date) throws DukeException {
        super(taskName);

        try {
            this.by = LocalDateTime.parse(
                    date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.originalFormat = this.by.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.by.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException ex) {
            Task.totalTasks--;
            throw new DukeException("You need to use the proper format!\n"
                    + "eg deadline return book /by 2019-10-15 2359");
        }
    }

    /**
     * Returns the string representation of the deadline task in the format to be saved in the computer.
     *
     * @return String representation of the task.
     */
    @Override
    public String toTaskData() {
        return "D" + " ; " + super.toTaskData() + " ; " + originalFormat;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printedFormat + ")";
    }
}
