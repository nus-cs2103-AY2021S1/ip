package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {

    public Deadline(String description, LocalDate date) {
        super(description, date);
    }

    public Deadline(String description, boolean isDone, LocalDate date) {
        super(description, isDone, date);
    }

    @Override
    public String toText() {
        return super.toText("D");
    }

    @Override
    public String toString() {
        return super.toString("D");
    }
}