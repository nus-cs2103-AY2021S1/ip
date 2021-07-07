package nekochan.model.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoStorageException;
import nekochan.exceptions.NekoTaskCreationException;
import nekochan.parser.DateParser;
import nekochan.util.Messages;

/**
 * The {@code Event} class represents an event with a scheduled time.
 * Extends the {@link Task} class.
 */
public class Event extends Task {

    private static final String EVENT_DELIMITER = "from";

    private static final String DURATION_DELIMITER = "for";

    private static final String END_TIME_DELIMITER = "to";

    private static final String ALL_DAY_KEYWORD = "all day";

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    private Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        startDateTime = start;
        endDateTime = end;
    }

    private Event(String description, LocalDateTime start, LocalDateTime end, boolean isCompleted) {
        super(description, isCompleted);
        startDateTime = start;
        endDateTime = end;
    }

    private static Event createAllDayEvent(String description, String dateTimeComponent) {
        String start = dateTimeComponent.split(ALL_DAY_KEYWORD)[0].split(DURATION_DELIMITER)[0].trim();
        LocalDateTime startDateTime = DateParser.parseStringToDateTime(start);
        return new Event(description, startDateTime, startDateTime);
    }

    private static Event createEventUsingEndDateTime(String description, String dateTimeComponent)
            throws NekoTaskCreationException {
        try {
            String start = dateTimeComponent.split(END_TIME_DELIMITER)[0].trim();
            String end = dateTimeComponent.split(END_TIME_DELIMITER)[1].trim();
            LocalDateTime startDateTime = DateParser.parseStringToDateTime(start);
            LocalDateTime endDateTime = DateParser.parseStringToDateTime(end);
            return new Event(description, startDateTime, endDateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NekoTaskCreationException(Messages.PARSE_EVENT_MISSING_END_DATETIME_ERROR);
        }
    }

    private static Event createEventUsingDuration(String description, String dateTimeComponent)
            throws NekoTaskCreationException {
        try {
            String start = dateTimeComponent.split(DURATION_DELIMITER)[0].trim();
            int duration = DateParser.parseDurationToMinutes(dateTimeComponent.split(DURATION_DELIMITER)[1]);
            LocalDateTime startDateTime = DateParser.parseStringToDateTime(start);
            LocalDateTime endDateTime = startDateTime.plusMinutes(duration);

            // Check if the endDateTime should be brought forward by 1 day to prevent over counting of days.
            // E.g. 3 days after 1 Jan 00:00 should end at 3 Jan 23:59 instead of 4 Jan 00:00.
            boolean isStartOfNewDay = endDateTime.isEqual(endDateTime.toLocalDate().atStartOfDay());
            boolean isDateOnly = DateParser.isDateOnly(endDateTime);
            boolean isDifferentFromStart = endDateTime.toLocalDate().isAfter(startDateTime.toLocalDate());
            if (isDateOnly && isStartOfNewDay && isDifferentFromStart) {
                endDateTime = endDateTime.minusDays(1);
            }

            return new Event(description, startDateTime, endDateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NekoTaskCreationException(Messages.PARSE_EVENT_MISSING_END_DATETIME_ERROR);
        }
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
            throw new NekoTaskCreationException(Messages.PARSE_COMMAND_EVENT_MISSING_ARGUMENT);
        }
        try {
            String description = details.substring(0, details.lastIndexOf(EVENT_DELIMITER)).trim();
            String dateTimeComponent = details.substring(details.lastIndexOf(EVENT_DELIMITER) + 4).trim().toLowerCase();
            if (dateTimeComponent.contains(ALL_DAY_KEYWORD)) {
                return createAllDayEvent(description, dateTimeComponent);
            } else if (dateTimeComponent.contains(END_TIME_DELIMITER)) {
                return createEventUsingEndDateTime(description, dateTimeComponent);
            } else if (dateTimeComponent.contains(DURATION_DELIMITER)) {
                return createEventUsingDuration(description, dateTimeComponent);
            } else {
                throw new NekoTaskCreationException(Messages.PARSE_EVENT_MISSING_END_DATETIME_ERROR);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new NekoTaskCreationException(Messages.PARSE_EVENT_DATETIME_ERROR);
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
            throw new NekoStorageException(Messages.DECODE_UNEXPECTED_TYPE_ERROR);
        }
        String[] content = code.split("\\|", 5);
        if (content.length != 5) {
            throw new NekoStorageException(Messages.STORAGE_ERROR_CORRUPT);
        }
        Event newEvent = new Event(content[4],
                DateParser.parseStringToDateTime(content[2]),
                DateParser.parseStringToDateTime(content[3]));
        if (content[1].equals(ENCODED_COMPLETE_FLAG)) {
            newEvent = newEvent.setCompleted();
        } else if (!content[1].equals(ENCODED_INCOMPLETE_FLAG)) {
            throw new NekoStorageException(Messages.STORAGE_ERROR_CORRUPT);
        }
        return newEvent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event setCompleted() {
        return new Event(description, startDateTime, endDateTime, true);
    }

    /**
     * Returns true if the specified {@code obj} is an {@code Event} and has the same (case insensitive) description,
     * start and end datetime as this {@code Event}.
     *
     * @param obj the reference object with which to compare.
     * @return true if the {@code Object} is an {@code Event} and are similar.
     */
    @Override
    boolean isSimilar(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }

        Event other = (Event) obj;
        return other.description.toLowerCase().equals(description.toLowerCase())
                && other.startDateTime.equals(startDateTime)
                && other.endDateTime.equals(endDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event deepCopy() {
        return new Event(description, startDateTime, endDateTime, isCompleted);
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

    /**
     * Returns an encoded string representation of this {@code Event}.
     *
     * @return an encoded string representation of this {@code Event}.
     */
    public String encode() {
        return String.format("E|%s|%s|%s|%s", super.isCompleted ? ENCODED_COMPLETE_FLAG : ENCODED_INCOMPLETE_FLAG,
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
            return searchParameter.toLowerCase().contains(description.toLowerCase())
                    || description.toLowerCase().contains(searchParameter.toLowerCase());
        }
    }

    /**
     * Returns true if the specified {@code obj} is an {@code Event} and has the same details.
     *
     * @param obj the reference object with which to compare.
     * @return true if the {@code Object} is an {@code Event} and has the same details.
     */
    @Override
    public boolean isDuplicate(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }

        Event other = (Event) obj;
        return other.description.equals(description)
                && other.startDateTime.equals(startDateTime)
                && other.endDateTime.equals(endDateTime);
    }
}
