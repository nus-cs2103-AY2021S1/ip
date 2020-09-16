package fei.task;

import fei.exception.FeiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * This method parse a deadline in format "yyyy-mm-dd" to "MMM d yyyy".
     *
     * @throws FeiException when the format is incorrect.
     */
    public void parseTime() throws FeiException {
        try {
            LocalDate ddl = LocalDate.parse(by);
            this.by = ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception exception) {
            throw FeiException.deadlineParseException();
        }
    }

    @Override
    public String savedFormat() {
        return "D " + super.savedFormat() + String.format(" | %s", by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
