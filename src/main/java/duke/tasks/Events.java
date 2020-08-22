package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents the a task that has a start date time.
 */
public class Events extends Task {
    private LocalDateTime startTime;

    /**
     * Constructs the events class.
     *
     * @param description the string of description of the events task.
     * @param startTime the LocalDateTime object that represents the events of the task.
     */
    public Events(String description, LocalDateTime startTime) {
        super(description);
        this.startTime = startTime;
    }


    /**
     * Returns the start datetime of the task.
     *
     * @return the LocalDateTime object of start dateTime.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the event date of the task.
     *
     * @return the localDate object of the task start time.
     */
    public LocalDate getDate() {
        return startTime.toLocalDate();
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation of event task, including status icon, description,
     *         and description.
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH : mm")) + ")";
    }
}
