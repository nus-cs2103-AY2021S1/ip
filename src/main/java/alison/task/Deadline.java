package alison.task;

import alison.exception.AlisonException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    private LocalDate ddl = null;

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
     * @throws AlisonException when the format is incorrect.
     */
    public void parseTime() throws AlisonException {
        try {
            this.ddl = LocalDate.parse(by);
            this.by = ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception exception) {
            throw AlisonException.deadlineParseException();
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
