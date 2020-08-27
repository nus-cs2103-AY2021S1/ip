package main.java.emily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    protected String timeStamp;


    public Deadline(String description, String timeStamp) {
        super(description);
        this.timeStamp = timeStamp;
        this.by = LocalDate.parse(timeStamp);
    }

    public String getBy() {
        return this.timeStamp;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }


}