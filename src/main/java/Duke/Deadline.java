package Duke;

import Duke.Exception.*;

public class Deadline extends Task {

    private final DateAndTime dateTime;

    public Deadline(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.dateTime = new DateAndTime(date, time);
    }

    @Override
    public String toData() {
        return checkIsDone()
                ? "D//1//" + getDescription() + "//" + this.dateTime.getDate() + "//" + this.dateTime.getTime()
                : "D//0//" + getDescription() + "//" + this.dateTime.getDate() + "//" + this.dateTime.getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }
}
