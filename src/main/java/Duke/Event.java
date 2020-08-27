package Duke;

import Duke.Exception.*;

public class Event extends Task {

    private final DateAndTime datetime;

    public Event(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.datetime = new DateAndTime(date, time);
    }

    @Override
    public String toData() {
        return checkDone()
                ? "E//1//" + getDescription() + "//" + this.datetime.getDate() + "//" + this.datetime.getTime()
                : "E//0//" + getDescription() + "//" + this.datetime.getDate() + "//" + this.datetime.getTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.datetime + ")";
    }
}
