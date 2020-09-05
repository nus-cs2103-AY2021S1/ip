package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;
    protected LocalDate eventDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            eventDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            //do nothing
        }
    }
    public void printTime() {
        try {
            System.out.println(eventDate.getMonth().toString()
                + " " + eventDate.getDayOfMonth()
                + " " + eventDate.getYear());
        } catch (NullPointerException e) {
            System.out.println("No valid date available.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        int stat = 0;
        if (isDone) {
            stat = 1;
        }
        return String.format("%s | %d | %s at %s", getType(), stat, description, at);
    }
}
