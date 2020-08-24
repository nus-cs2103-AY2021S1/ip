package tasks;

import exceptions.DataException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    // task must be done by this time
    private final LocalDate by;

    public Deadline(String desc, String by) throws DataException {
        super(desc);
        if (by.isBlank()) {
            throw new DataException("Time Due", "Cannot be blank");
        }
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DataException("Time Due", "Parse error - " + e.getMessage());
        }
    }

    @Override
    protected char getTaskType() {
        return 'D';
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(),
                by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @Override
    public String getParentCommand() {
        return "deadline " + getDescription() + " /by " + by;
    }
}
