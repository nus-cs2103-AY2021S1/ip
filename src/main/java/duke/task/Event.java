package duke.task;

import duke.parser.DateParser;
import duke.exceptions.DukeException;

import java.time.LocalDateTime;

public class Event extends Task {

    private static final String EVENT_DELIMITER = "/at";

    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public static Event createTask(String details) {
        if (details == null) {
            throw new DukeException("I need something to work with.");
        }
        String[] detailsArray = details.split(EVENT_DELIMITER, 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateParser.parseString(dateTimeString);
            return new Event(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wow that sure is one long event...");
        }
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + DateParser.parseLocalDateTime(this.dateTime) + ")";
    }

    public String encode() {
        return String.format("E|%s|%s|%s", super.completed ? "Y" : "N", DateParser.parseLocalDateTime(this.dateTime), super.description);
    }

    public static Event decode(String code) throws DukeException {
        if (code.charAt(0) == 'E') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("There are some holes in my memory...");
            }
            Event newEvent = new Event(content[3], DateParser.parseString(content[2]));
            if (content[1].equals("Y")) {
                newEvent.setCompleted();
            } else if (!content[1].equals("N")) {
                throw new DukeException("There are some holes in my memory...");
            }
            return newEvent;
        } else {
            throw new DukeException("Something doesn't seem right...");
        }
    }
}
