package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String identity;
    private LocalDate date;
    String[] desArray, timeArray;

    public Event(String description) {
        super(description);
        this.identity = "E";
        desArray = this.description.split("/", 2);
        timeArray = desArray[1].split(" ", 2);
        this.date = LocalDate.parse(timeArray[1]);
    }

    public String getIdentity() {
        return identity;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + "("
                + timeArray[0] + ": " + getDate() + ")";
    }
}