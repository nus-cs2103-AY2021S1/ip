package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventDate;

    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public static Event load(String eventDetails) {
        String[] splitEventDetails = eventDetails.split("\\|",4);
        Event event = new Event(splitEventDetails[2], LocalDate.parse(splitEventDetails[3]));
        if (splitEventDetails[1].equals("true")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String saveAs() {
        return "E | " + super.saveAs() + " | " + eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate.format(
                DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}