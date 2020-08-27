package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
        this.dateString = by;
    }

    @Override
    public String toFileString() {
        String doneInteger = isDone ? "1" : "0";
        return "D | " + doneInteger + " | " + this.description + " | " + this.dateString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}