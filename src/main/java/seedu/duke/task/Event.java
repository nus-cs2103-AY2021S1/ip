package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an event task.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, boolean status) {
        super(name, status);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns what the current task is and its status.
     * @return String representing the task.
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: "
                + this.startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy kkmm")) + " to "
                + this.endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy kkmm")) + ")";
    }

    /**
     * Gets the starting time of the task.
     * @return DateTime object that represents the start time.
     */
    @Override
    public LocalDateTime getTime() {
        return this.startTime;
    }

    /**
     * Returns the ending time of the task, if any.
     * @return LocalDateTime object that is the ending time of the task, if any.
     */
    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the type of the task.
     * @return String that represents the type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }
}
