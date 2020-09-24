package tickbot.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The class to represent a deadline task.
 */
public class Deadline extends Task {
    public Deadline(boolean completed, String content, LocalDateTime time) {
        super(completed, content, time, new ArrayList<>());
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
