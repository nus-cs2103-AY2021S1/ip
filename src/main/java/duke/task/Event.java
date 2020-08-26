package duke.task;

import duke.exceptions.DukeException;
import duke.exceptions.DukeStorageException;
import duke.exceptions.DukeTaskCreationException;
import duke.parser.DateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The {@code Event} class represents an event with a scheduled time.
 * Extends the {@link Task} class.
 */
public class Event extends Task {

    private static final String EVENT_DELIMITER = "/at";

    private LocalDateTime dateTime;

    private Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns an {@code Event} object with the specified details.
     *
     * @param details the description and time of the event.
     * @return an {@code Event} object with the specified details.
     * @throws DukeTaskCreationException if format of the specified details is not recognised.
     */
    public static Event createTask(String details) throws DukeTaskCreationException {
        if (details == null) {
            throw new DukeTaskCreationException("I need something to work with.");
        }
        String[] detailsArray = details.split(EVENT_DELIMITER, 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateParser.parseString(dateTimeString);
            return new Event(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeTaskCreationException("Wow that sure is one long event...");
        }
    }

    /**
     * Decodes an encoded string into an {@code Event} object.
     *
     * @param code the encoded string.
     * @return an {@code Event} reconstructed from the encoded string.
     * @throws DukeStorageException if format of the code is incorrect.
     */
    public static Event decode(String code) throws DukeStorageException {
        if (code.charAt(0) == 'E') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            Event newEvent = new Event(content[3], DateParser.parseString(content[2]));
            if (content[1].equals("Y")) {
                newEvent.setCompleted();
            } else if (!content[1].equals("N")) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            return newEvent;
        } else {
            throw new DukeStorageException("Something doesn't seem right...");
        }
    }

    /**
     * Returns an encoded string representation of this {@code Event}.
     *
     * @return an encoded string representation of this {@code Event}.
     */
    public String encode() {
        return String.format("E|%s|%s|%s", super.completed ? "Y" : "N",
                DateParser.parseLocalDateTime(dateTime),
                super.description);
    }

    @Override
    public boolean match(String searchParameter) {
        try {
            LocalDate searchDate = DateParser.parseString(searchParameter).toLocalDate();
            if (searchDate.isEqual(dateTime.toLocalDate())) {
                return true;
            }
        } catch (DukeException e) {
        }
        return searchParameter.contains(description) || description.contains(searchParameter);
    }

    /**
     * Returns a string representation of this {@code Event} object.
     *
     * @return a string representation of this {@code Event} object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateParser.parseLocalDateTime(dateTime) + ")";
    }

}
