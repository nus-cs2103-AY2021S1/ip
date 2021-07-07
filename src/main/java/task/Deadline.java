package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DeadlineInvalidUsageException;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Represents a deadline object
     *
     * @param description deadline description
     * @param by          deadline date
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Factory method for {@code Deadline} object
     *
     * @param deadline deadline description
     * @param by       deadline date in ISO-8601 format
     * @return the Deadline object
     * @throws DeadlineInvalidUsageException on empty description or wrong date format
     */
    public static Deadline create(String deadline, String by) throws DeadlineInvalidUsageException {
        if (deadline.equals("")) {
            throw new DeadlineInvalidUsageException("Deadline description cannot be empty.");
        }

        try {
            return new Deadline(deadline, parseDate(by));
        } catch (DateTimeParseException ex) {
            throw new DeadlineInvalidUsageException("Deadline date must be of the form yyyy-mm-dd.");
        }
    }

    private static LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    private static String showDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }

    /**
     * Show task's name and status
     *
     * @return a string that represents the task
     */
    @Override
    public String showTask() {
        return String.format("[%s]%s (by: %s)", this.getType(), super.showTask(), showDate(this.by));
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getStorageDescription() {
        return super.getDescription() + " | " + this.by;
    }

    public LocalDate getDate() {
        return this.by;
    }
}
