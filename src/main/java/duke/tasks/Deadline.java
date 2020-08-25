package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    int code = 1;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(this.by);
    }

    public static void invalidInput() {
        invalidInput("OOPS!!! The format of the Deadline is wrong.");
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateFormatted() + ")";
    }

}