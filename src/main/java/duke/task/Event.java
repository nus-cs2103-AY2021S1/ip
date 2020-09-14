package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.parser.DateParser;

/**
 * Represents an event task
 */
public class Event extends Task {
    private String eventTime;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean isInDateFormat;

    /**
     * Creates an <code>Event</code> object
     * @param description The description of the event
     * @param eventTime The duration which the event happens
     */
    public Event(String description, String eventTime) {
        super(description);
        parseEventTime(eventTime);
    }

    private void parseEventTime(String eventTime) {
        assert eventTime != null : "event time cannot be null";
        this.eventTime = eventTime;
        isInDateFormat = false;
        int idx = eventTime.indexOf(" to ");
        if (idx != -1) {
            Optional<LocalDate> optStart = DateParser.parse(eventTime.substring(0, idx).trim());
            Optional<LocalDate> optEnd = DateParser.parse(eventTime.substring(idx + 4).trim());
            if (optStart.isPresent() && optEnd.isPresent()) {
                startTime = optStart.get();
                endTime = optEnd.get();
                if (endTime.compareTo(startTime) >= 0) { // if end happens after start
                    isInDateFormat = true;
                }
            }
        }
    }

    /**
     * Represents the type of this task through an icon.
     * @return An icon
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Converts the task to string.
     * @return The string representation of this task
     */
    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description + " (at: "
                + (isInDateFormat
                    ? "from " + startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                        + " to " + endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                    : eventTime)
                + ")";
    }

    /**
     * Checks whether this task is occurring on a specified day.
     * @param cmpDate A date that is being queried
     * @return True if this task occurs on that day, false otherwise
     */
    public boolean isOccurringOn(LocalDate cmpDate) {
        assert cmpDate != null : "compared date cannot be null";
        if (!isInDateFormat) {
            return false;
        }
        return cmpDate.compareTo(startTime) > 0 && cmpDate.compareTo(endTime) < 0;
    }

    public void setTime(String newEventTime) {
        parseEventTime(newEventTime);
    }

    /**
     * Gets the time that this task is due.
     * @return The time string input by the user
     */
    @Override
    public String getTime() {
        return eventTime;
    }

    @Override
    public Task clone() {
        Task clonedTask = new Event(super.description, eventTime);
        return this.isDone ? clonedTask.markAsDone() : clonedTask;
    }
}
