package Duke;

import Duke.Exception.*;

public class Event extends Task {

    private final DateAndTime dateTime;

    public Event(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.dateTime = new DateAndTime(date, time);
    }

    @Override
    public String toData() {
        return checkIsDone()
                ? "E//1//" + getDescription() + "//" + this.dateTime.getDate() + "//" + this.dateTime.getTime()
                : "E//0//" + getDescription() + "//" + this.dateTime.getDate() + "//" + this.dateTime.getTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }
}
