package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Creates an event object.
     * @param description Description of the event.
     * @param time Time of the event.
     * @throws DukeException If time format is not correct.
     */
    public Event(String description, String time) throws DukeException {
        super(description);
        String[] timeDetails = time.split("\\s", 3);
        if (timeDetails.length < 3) {
            throw new DukeException("OOPS! Wrong Date/Time format! Type 'help' to see the correct format");
        }
        this.startDateTime = parseEventDateTime(timeDetails[0], timeDetails[1]);
        this.endDateTime = parseEventDateTime(timeDetails[0], timeDetails[2]);
    }

    /**
     * Creates an event object with done/not done status.
     * @param isDone Whether the event is done.
     * @param description Description of the event.
     * @param time Time of the event.
     * @throws DukeException If time format is not correct.
     */
    public Event(boolean isDone, String description, String time) throws DukeException {
        super(isDone, description);
        String[] timeDetails = time.split("\\s", 3);
        this.startDateTime = parseEventDateTime(timeDetails[0], timeDetails[1]);
        this.endDateTime = parseEventDateTime(timeDetails[0], timeDetails[2]);
    }

    /**
     * Converts string date and time to LocalDateTime.
     * @param date String date input.
     * @param time String time input.
     * @return Converted LocalDateTime object.
     * @throws DukeException If date format is wrong.
     */
    private static LocalDateTime parseEventDateTime(String date, String time) throws DukeException {
        try {
            LocalDate datePart = LocalDate.parse(date);
            LocalTime timePart = LocalTime.parse(time);
            return LocalDateTime.of(datePart, timePart);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS! Wrong Date/Time format! Type 'help' to see the correct format");
        }
    }

    @Override
    String getTaskDetailsForSave() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " 
                + startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " "
                + startDateTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " "
                + endDateTime.format(DateTimeFormatter.ofPattern("HH:mm")) ;
    }

    @Override
    public String toString() {
        String formattedStartDateTime = startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String formattedEndDateTime = endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + super.toString()
                + String.format(" (at: %s to %s)", formattedStartDateTime, formattedEndDateTime);
    }
}
