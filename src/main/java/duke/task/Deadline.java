package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileString() {
        String done = super.isDone ? "1" : "0";
        return "D | " + done + " | " + super.description + " | " +  by;
    }

    @Override
    public String toString() {
        LocalDate localDate = LocalDate.parse(by);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}