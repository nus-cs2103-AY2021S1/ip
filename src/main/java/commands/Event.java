package commands;

import java.time.LocalDateTime;

import exceptions.DukeException;
import ui.DateConverter;

/**
 * Commands.Event class creates a type of Commands.Task called event which contains a date and description of the task
 */
public class Event extends Task {


    private LocalDateTime date;

    /**
     * constructor to make Commands.Event
     *
     * @param description
     * @param date
     */
    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * creates a Commands.Event from string
     *
     * @param details input from user
     * @return Commands.Event object
     */
    public static Event createEvent(String details) {
        if (details == null) {
            throw new DukeException("luigi thinks you are missing details");
        }
        String[] detailsArray = details.split("/at", 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateConverter.parseString(dateTimeString);
            return new Event(description, dateTime);
        } catch (Exception e) {
            throw new DukeException("No valid date found");
        }
    }

    /**
     * converts a String into a condensed form
     *
     * @return condesnsed form of inputted string
     */
    public String encode() {
        return String.format("E|%s|%s|%s", super.isDone ? "Y" : "N", this.date, super.description);
    }

    /**
     * unravels encoded Strings
     *
     * @param code String that has been previously encoded()
     * @return Commands.Event object
     * @throws DukeException in the event it is unable to decode the string
     */
    public static Event decode(String code) throws DukeException {
        if (code.charAt(0) == 'E') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("data string is not equal to 4");
            }
            Event newEvent = new Event(content[3], DateConverter.parseString(content[2]));
            if (content[1].equals("Y")) {
                newEvent.markAsDone();
            }
            return newEvent;
        } else {
            throw new DukeException("Unable to decode event");
        }
    }

    /**
     * overrides Commands.Event String output to be formatted
     *
     * @return String of formatted Commands.Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateConverter.parseLocalDateTime(this.date) + ")";
    }
}
