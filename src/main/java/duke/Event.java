package duke;

import exception.EventInvalidUsageException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;

    private Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public static Event create(String event) throws EventInvalidUsageException {
        String[] parsedEvent = event.split("\\s*/at\\s*", 2);

        if (parsedEvent.length < 2) {
            throw new EventInvalidUsageException("You should specify a date by using `/at`");
        }
        if (parsedEvent[0].equals("")) {
            throw new EventInvalidUsageException("Event description cannot be empty.");
        }

        try {
            return new Event(parsedEvent[0], parseDate(parsedEvent[1]));
        } catch (DateTimeParseException ex) {
            throw new EventInvalidUsageException("Deadline date must be of the form yyyy-mm-dd.");
        }
    }

    public static Event create(String event, String at) throws EventInvalidUsageException {
        if (event.equals("")) {
            throw new EventInvalidUsageException("Event description cannot be empty.");
        }

        try {
            return new Event(event, parseDate(at));
        } catch (DateTimeParseException ex) {
            throw new EventInvalidUsageException("Event date must be of the form yyyy-mm-dd.");
        }
    }

    @Override
    public String showTask() {
        return String.format("[%s]%s (at: %s)", this.getType(), super.showTask(), showDate(this.at));
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.at;
    }

    private static LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    private static String showDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }
}
