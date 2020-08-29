package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDateTime time;

    /**
     * Default Constructor for Event Task
     * @param name Name of Task
     * @param time Time of Task
     */
    public EventTask(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Alternative Constructor for Event Task
     * @param name Name of Task
     * @param hasCompleted boolean to show if task is completed
     * @param time Time of Task
     */
    public EventTask(String name, int hasCompleted, LocalDateTime time) {
        super(name, hasCompleted);
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
}
