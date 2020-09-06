package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** A task of type Event. */
public class Event extends Task {

    /** Save format for empty endDate. */
    public static final String EMPTY_END_DATE = "XXXXXXXXXXXXXXXXXXX";

    /** The starting date time of the event. */
    private final LocalDateTime startDate;

    /** The end date time of the event. */
    private final LocalDateTime endDate;

    /**
     * Constructs an Event.
     *
     * @param task      The description of the event.
     * @param startDate The starting date of the event.
     * @param endDate   The end date of the event.
     * @param isDone    The completion status of the event.
     */
    private Event(String task, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        super(task, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an uncompleted Event.
     *
     * @param task      The description of the event.
     * @param startDate The starting date of the event.
     * @param endDate   The end date of the event.
     */
    public Event(String task, LocalDateTime startDate, LocalDateTime endDate) {
        this(task, startDate, endDate, false);
    }

    /**
     * Constructs an uncompleted Event.
     *
     * @param task The description of the event.
     * @param date The date of the event.
     */
    public Event(String task, LocalDateTime date) {
        this(task, date, null);
    }

    /**
     * The String format of the date.
     *
     * @return The desired date time format.
     */
    private String dateFormat() {
        if (endDate != null) {
            return String.format(" (at: %s to %s)",
                startDate.format(DATE_TIME_FORMAT),
                startDate.toLocalDate().equals(endDate.toLocalDate())
                    ? endDate.format(TIME_FORMAT)
                    : endDate.format(DATE_TIME_FORMAT));
        } else {
            return String.format(" (at: %s)",
                startDate.format(DATE_TIME_FORMAT));
        }
    }

    /**
     * The String format of the date used for saving.
     *
     * @return The desired date time format used for saving.
     */
    private String dateSaveFormat() {
        String startDateString = String.format("%sT%s",
            startDate.format(SAVE_DATE_FORMAT),
            startDate.format(SAVE_TIME_FORMAT));

        String endDateString = endDate != null
            ? String.format("%sT%s", endDate.format(SAVE_DATE_FORMAT),
            endDate.format(SAVE_TIME_FORMAT))
            : EMPTY_END_DATE;

        return startDateString + " to " + endDateString;
    }

    /**
     * Marks the event as done.
     *
     * @return The same event with a status of completed.
     */
    @Override
    public Event markDone() {
        return new Event(task, startDate, endDate, true);
    }

    @Override
    public LocalDate getDate() {
        return startDate.toLocalDate();
    }

    /**
     * Compares with another object.
     *
     * @param obj The object compared.
     * @return True if the object compared is a Event with the same task, start and end dates.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event event = (Event) obj;

            boolean isSameTask = event.task.equals(this.task);
            boolean isSameStartDate = event.startDate.equals(startDate);
            boolean isSameEndDate = event.endDate != null && endDate != null
                    ? event.endDate.equals(endDate)
                    : event.endDate == null && endDate == null;

            return isSameTask && isSameStartDate && isSameEndDate;
        }

        return false;
    }

    /**
     * The format used for saving.
     *
     * @return The String format used for saving.
     */
    @Override
    public String saveFormat() {
        return "E" + super.saveFormat() + dateSaveFormat();
    }

    /**
     * The format used to display on a list.
     *
     * @return The String format of a deadline.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + dateFormat();
    }
}
