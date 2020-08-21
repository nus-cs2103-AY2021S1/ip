package duke;

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

    public static Deadline create(String deadline) throws DeadlineInvalidUsageException {
        String[] parsedDeadline = deadline.split("\\s*/by\\s*", 2);

        if (parsedDeadline.length < 2) {
            throw new DeadlineInvalidUsageException("You should specify the deadline by using `/by`");
        }
        if (parsedDeadline[0].equals("")) {
            throw new DeadlineInvalidUsageException("Deadline description cannot be empty.");
        }

        try {
            return new Deadline(parsedDeadline[0], parseDate(parsedDeadline[1]));
        } catch (DateTimeParseException ex) {
            throw new DeadlineInvalidUsageException("Deadline date must be of the form yyyy-mm-dd.");
        }
    }

    @Override
    public String showTask() {
        return String.format("[D]%s (by: %s)", super.showTask(), showDate(this.by));
    }

    private static LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    private static String showDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }
}
