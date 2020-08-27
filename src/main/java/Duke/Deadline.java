package Duke;

import Duke.Exception.*;

public class Deadline extends Task {

    private final DateAndTime datetime;

    public Deadline(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.datetime = new DateAndTime(date, time);
    }

    @Override
    public String toData() {
        return checkDone()
                ? "D//1//" + getDescription() + "//" + this.datetime.getDate() + "//" + this.datetime.getTime()
                : "D//0//" + getDescription() + "//" + this.datetime.getDate() + "//" + this.datetime.getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.datetime + ")";
    }
}
