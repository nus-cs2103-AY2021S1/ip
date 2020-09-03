package nekochan.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoStorageException;
import nekochan.exceptions.NekoTaskCreationException;
import nekochan.parser.DateParser;

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
     * Returns an {@code Event} object with the specified {@code details}.
     *
     * @param details the description and time of the event.
     * @return an {@code Event} object with the specified details.
     * @throws NekoTaskCreationException if format of the specified details is not recognised.
     */
    public static Event createTask(String details) throws NekoTaskCreationException {
        if (details == null) {
            throw new NekoTaskCreationException("I need something to work with.");
        }
        try {
            String description = details.substring(0, details.lastIndexOf(EVENT_DELIMITER)).trim();
            String dateTimeComponent = details.substring(details.lastIndexOf(EVENT_DELIMITER) + 4).trim().toLowerCase();
            if (dateTimeComponent.contains(ALL_DAY_KEYWORD)) {
                String start = dateTimeComponent.split(ALL_DAY_KEYWORD)[0].split(DURATION_DELIMITER)[0].trim();
                LocalDateTime startDateTime = DateParser.parseStringToDateTime(start);
                LocalDateTime endDateTime = startDateTime.withHour(23).withMinute(59);
                return new Event(description, startDateTime, endDateTime);
            } else if (dateTimeComponent.contains(END_TIME_DELIMITER)) {
                try {
                    String start = dateTimeComponent.split(END_TIME_DELIMITER)[0].trim();
                    String end = dateTimeComponent.split(END_TIME_DELIMITER)[1].trim();
                    LocalDateTime startDateTime = DateParser.parseStringToDateTime(start);
                    LocalDateTime endDateTime = DateParser.parseStringToDateTime(end);
                    return new Event(description, startDateTime, endDateTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NekoTaskCreationException("Does this thing ever end???");
                }
            } else if (dateTimeComponent.contains(DURATION_DELIMITER)) {
                try {
                    String start = dateTimeComponent.split(DURATION_DELIMITER)[0].trim();
                    int duration = DateParser.parseDurationToMinutes(dateTimeComponent.split(DURATION_DELIMITER)[1]);
                    LocalDateTime startDateTime = DateParser.parseStringToDateTime(start);
                    LocalDateTime endDateTime = startDateTime.plusMinutes(duration);
                    return new Event(description, startDateTime, endDateTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NekoTaskCreationException("Does this thing ever end???");
                }
            } else {
                throw new NekoTaskCreationException("Wow that sure is one long event.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new NekoTaskCreationException("Something's missing, oh right I lost track of time.");
        }
    }

    /**
     * Decodes an encoded string into an {@code Event} object.
     *
     * @param code the encoded string.
     * @return an {@code Event} reconstructed from the encoded string.
     * @throws NekoStorageException if format of the code is incorrect.
     */
    public static Event decode(String code) throws NekoStorageException {
        if (code.charAt(0) != 'E') {
            throw new NekoStorageException("Something doesn't seem right...");
        }
        String[] content = code.split("\\|", 5);
        if (content.length != 5) {
            throw new NekoStorageException("There are some holes in my memory...");
        }
        Event newEvent = new Event(content[4],
                DateParser.parseStringToDateTime(content[2]),
                DateParser.parseStringToDateTime(content[3]));
        if (content[1].equals("Y")) {
            newEvent.setCompleted();
        } else if (!content[1].equals("N")) {
            throw new NekoStorageException("There are some holes in my memory...");
        }
        return newEvent;
    }

    /**
     * Returns an encoded string representation of this {@code Event}.
     *
     * @return an encoded string representation of this {@code Event}.
     */
    public String encode() {
        return String.format("E|%s|%s|%s|%s", super.isCompleted ? "Y" : "N",
                DateParser.parseLocalDateTimeToString(startDateTime),
                DateParser.parseLocalDateTimeToString(endDateTime),
                super.description);
    }

    /**
     * Returns true if this {@code Event} matches the specified {@code searchParameter}.
     *
     * @param searchParameter the string to test against.
     * @return true if this {@code Event} matches the specified {@code searchParameter}.
     */
    @Override
    public boolean match(String searchParameter) {
        try {
            LocalDate searchDate = DateParser.parseStringToDateTime(searchParameter).toLocalDate();
            boolean isEqualStart = searchDate.isEqual(startDateTime.toLocalDate());
            boolean isEqualEnd = searchDate.isEqual(endDateTime.toLocalDate());
            boolean isBetween = searchDate.isBefore(endDateTime.toLocalDate())
                    && searchDate.isAfter(startDateTime.toLocalDate());
            return isEqualStart || isEqualEnd || isBetween;
        } catch (NekoException e) {
            return searchParameter.contains(description) || description.contains(searchParameter);
        }
    }

    /**
     * Returns a string representation of this {@code Event} object.
     *
     * @return a string representation of this {@code Event} object.
     */
    @Override
    public String toString() {
        if (DateParser.isDateOnly(startDateTime) && startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return "[E]" + super.toString() + " (on " + DateParser.parseLocalDateTimeToString(startDateTime)
                    + " for all day)";
        } else {
            return "[E]" + super.toString() + " (from " + DateParser.parseLocalDateTimeToString(startDateTime)
                    + " to " + DateParser.parseLocalDateTimeToString(endDateTime) + ")";
        }
    }

}
