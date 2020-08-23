package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String deadlineDate;

    public Deadline(String deadlineName, String deadlineDate) {
        super(deadlineName);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String deadlineName, LocalDate localDate) {
        super(deadlineName, true, localDate);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (super.hasDate() ? localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadlineDate) + ")";
    }

    @Override
    public String getSaveFormat() {
        return "D" + " | " + super.getSaveFormat() + " | " + (super.hasDate() ? localDate : deadlineDate);
    }
}
