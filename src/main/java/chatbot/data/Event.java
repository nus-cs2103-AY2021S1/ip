package chatbot.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String description, boolean isDone, LocalDate date) {
        super(description, "E", isDone, date);
    }

    public static Event newEvent(String description, LocalDate date) {
        return new Event(description, false, date);
    }

    @Override
    public Event markDone() {
        return new Event(this.description, true, this.date);
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + " (at: " + formattedDate + ")";
    }
}
