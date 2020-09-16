package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final LocalDateTime time;

    /**
     * Initialisation of the event class.
     * @param description The description about the task
     * @param time The date and time when this event is happening
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getDate() {
        return time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public LocalDateTime getActualDate() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

}
