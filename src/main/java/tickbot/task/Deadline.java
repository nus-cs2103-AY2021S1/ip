package tickbot.task;

import java.time.LocalDate;

/**
 * The class to represent a deadline task.
 */
public class Deadline extends Task {
    public Deadline(boolean completed, String content, LocalDate time) {
        super(completed, content, time);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getTimeMarker() {
        return "by";
    }
}