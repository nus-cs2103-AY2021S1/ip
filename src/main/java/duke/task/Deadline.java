package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    private String by;
    private LocalDate byLocalDate;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            this.byLocalDate = LocalDate.parse(by);
        } catch (Exception e) {
            throw new DukeException("Date is not in YYYY-MM-DD format");
        }
    }

    @Override
    public String getStringType() {
        return "D";
    }

    @Override
    public Optional<String> getDate() {
        return Optional.of(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                byLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
