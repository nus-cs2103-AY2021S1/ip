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

    private static final String EVENT_DELIMITER = "from";

    private static final String DURATION_DELIMITER = "for";

    private static final String END_TIME_DELIMITER = "to";

    private static final String ALL_DAY_KEYWORD = "all day";

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        startDateTime = start;
        endDateTime = end;
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
        try {
            String description = details.substring(0, details.lastIndexOf(EVENT_DELIMITER)).trim();
            String dateTimeComponent = details.substring(details.lastIndexOf(EVENT_DELIMITER) + 4).trim().toLowerCase();
            if (dateTimeComponent.contains(ALL_DAY_KEYWORD)) {
                String start = dateTimeComponent.split(ALL_DAY_KEYWORD)[0].split(DURATION_DELIMITER)[0].trim();
                LocalDateTime startDateTime = DateParser.parseString(start);
                LocalDateTime endDateTime = startDateTime.withHour(23).withMinute(59);
                return new Event(description, startDateTime, endDateTime);
            } else if (dateTimeComponent.contains(END_TIME_DELIMITER)) {
                try {
                    String start = dateTimeComponent.split(END_TIME_DELIMITER)[0].trim();
                    String end = dateTimeComponent.split(END_TIME_DELIMITER)[1].trim();
                    LocalDateTime startDateTime = DateParser.parseString(start);
                    LocalDateTime endDateTime = DateParser.parseString(end);
                    return new Event(description, startDateTime, endDateTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeTaskCreationException("Does this thing ever end???");
                }
            } else if (dateTimeComponent.contains(DURATION_DELIMITER)) {
                try {
                    String start = dateTimeComponent.split(DURATION_DELIMITER)[0].trim();
                    int duration = DateParser.parseDuration(dateTimeComponent.split(DURATION_DELIMITER)[1]);
                    LocalDateTime startDateTime = DateParser.parseString(start);
                    LocalDateTime endDateTime = startDateTime.plusMinutes(duration);
                    return new Event(description, startDateTime, endDateTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeTaskCreationException("Does this thing ever end???");
                }
            } else {
                throw new DukeTaskCreationException("Wow that sure is one long event.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeTaskCreationException("Something's missing, oh right I lost track of time.");
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
            if (content.length != 5) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            Event newEvent = new Event(content[3],
                    DateParser.parseString(content[2]),
                    DateParser.parseString(content[3]));
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
        return String.format("E|%s|%s|%s|%s", super.completed ? "Y" : "N",
                DateParser.parseLocalDateTime(startDateTime),
                DateParser.parseLocalDateTime(endDateTime),
                super.description);
    }

    @Override
    public boolean match(String searchParameter) {
        try {
            LocalDate searchDate = DateParser.parseString(searchParameter).toLocalDate();
            if (searchDate.isEqual(startDateTime.toLocalDate()) || searchDate.isEqual(endDateTime.toLocalDate())
                    || (searchDate.isBefore(endDateTime.toLocalDate())
                    && searchDate.isAfter(startDateTime.toLocalDate()))) {
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
        if (DateParser.isDateOnly(startDateTime) && startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return "[E]" + super.toString() + " (on " + DateParser.parseLocalDateTime(startDateTime)
                    + " for all day)";
        } else {
            return "[E]" + super.toString() + " (from " + DateParser.parseLocalDateTime(startDateTime)
                    + " to " + DateParser.parseLocalDateTime(endDateTime) + ")";
        }
    }

}
