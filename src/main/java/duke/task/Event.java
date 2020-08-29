package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** A task of type Event. */
public class Event extends Task {

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
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an uncompleted Event.
     *
     * @param task The description of the event.
     * @param date The date of the event.
     */
    public Event(String task, LocalDateTime date) {
        super(task);
        this.startDate = date;
        this.endDate = null;
    }

    /**
     * The String format of the date.
     */
    private String dateFormat() {
        if (endDate != null) {
            return String.format(" (at: %s to %s)",
                startDate.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")),
                startDate.toLocalDate().equals(endDate.toLocalDate())
                    ? endDate.format(DateTimeFormatter.ofPattern("h:mm a"))
                    : endDate.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")));
        } else {
            return String.format(" (at: %s)",
                startDate.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")));
        }
    }

    /** The String format of the date used for saving. */
    private String dateSaveFormat() {
        String startDateString = String.format("%sT%s",
            startDate.format(DateTimeFormatter.ofPattern("y-MM-dd")),
            startDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        String endDateString = endDate != null
            ? String.format("%sT%s", endDate.format(DateTimeFormatter.ofPattern("y-MM-dd")),
            endDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")))
            : "XXXXXXXXXXXXXXXXXXX";

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
     * @param o The object compared.
     * @return True if the object compared is a Event with the same task, start and end dates.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Event) {
            Event e = (Event) o;

            if (e.endDate == null && endDate == null) {
                return e.task.equals(this.task) && e.startDate.equals(startDate);
            } else if (e.endDate != null && endDate != null) {
                return e.task.equals(this.task) && e.startDate.equals(startDate)
                    && e.endDate.equals(endDate);
            } else {
                return false;
            }

        } else {
            return false;
        }
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
