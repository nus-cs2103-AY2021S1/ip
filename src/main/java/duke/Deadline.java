package duke;

import java.time.LocalDate;

public class Deadline extends Task {

    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}