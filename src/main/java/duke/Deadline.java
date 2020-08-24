package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

//    protected String by;
    protected DukeDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = new DukeDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String fileFormat() {
        return "D , " + super.fileFormat() + by.getStringDate();
    }
}