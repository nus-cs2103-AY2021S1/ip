package duke.task;

import java.time.LocalDate;

/**
 * Represents a Deadline Task. A <code>Deadline</code> object contains a description,
 * keeps track of whether it has been completed as well as the time.
 */
public class Deadline extends TimedTask {

    /**
     * Constructor for Deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, deadline);
        super.type = Task.Type.DEADLINE;
    }

    /**
     * Constructor for Deadline with additional isDone parameter.
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, deadline);
        super.type = Task.Type.DEADLINE;
        super.isDone = isDone;
    }

    public String getTypeIcon() {
        String icon = "[D]";
        return icon;
    }

    public void snooze() {
        super.date = super.date.plusDays(1);
    }
}
