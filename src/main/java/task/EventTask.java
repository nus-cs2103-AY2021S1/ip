package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDateTime time;

    /**
     * Default Constructor for Event Task
     *
     * @param name Name of Task.
     * @param time Time of Task.
     */
    public EventTask(String name, LocalDateTime time) {
        super(name, TaskType.EVENT);
        this.time = time;
    }

    /**
     * Alternative Constructor for Event Task
     *
     * @param name            Name of Task.
     * @param hasCompletedInt boolean to show if task is completed.
     * @param time            Time of Task.
     */
    public EventTask(String name, int hasCompletedInt, LocalDateTime time) {
        super(name, TaskType.EVENT, hasCompletedInt);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }

    /**
     * Formats task into a string for file saving
     *
     * @return formatted string representing the task.
     */
    public String getFormattedString() {
        return String.format(
                "E | %s | %s | %s\n",
                this.getHasCompletedInt(), this.getName(), this.getTime());
    }
}
