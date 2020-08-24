package duke.task;

import exception.DeadlineInvalidUsageException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    private Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

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

    @Override
    public String showTask() {
        return String.format("[%s]%s (by: %s)", this.getType(), super.showTask(), showDate(this.by));
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.by;
    }

    public LocalDate getDate() {
        return this.by;
    }

    private static LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    private static String showDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }
}
