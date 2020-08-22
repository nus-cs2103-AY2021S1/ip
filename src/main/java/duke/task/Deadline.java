package duke.task;

import duke.exception.InvalidDateInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String identifier = "D";
    private final LocalDate by;

    public Deadline(String description, String by) throws InvalidDateInputException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(by);
        }
    }

    public Deadline(String description, String by, boolean isDone)
            throws InvalidDateInputException {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException(by);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.identifier, super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialise() {
        return String.format("%s | %s | %s", Deadline.identifier, super.serialise(),
                this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
