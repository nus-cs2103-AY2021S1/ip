package olivia.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DatedTask class that represents a Task with a Date.
 */

public abstract class DatedTask extends Task {

    /** Constant Strings to check which field to change */
    private static final String DESCRIPTION = "/description";
    private static final String TIME = "/time";

    protected LocalDateTime time;

    /**
     * Constructor that creates a DatedTask object that has a description of the
     * task, a tag representing the type of task, a time representing the date of the task,
     * and whether the task has been completed. Protected so only subclasses of DatedTask
     * can access and create a DatedTask object, to prevent any instances from being
     * directly created.
     *
     * @param description a String representing the description of the task
     * @param tag a String representing the type of task
     * @param time a LocalDateTime representing the date and time of the task
     * @param isDone a boolean representing whether the task has been completed
     */

    protected DatedTask(String description, String tag, String time, boolean isDone) {
        super(description, "E", isDone);
        this.time = LocalDateTime.parse(time,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Updates the description or the time of the task to the given String, depending
     * on the field selected.
     * @param str a String representing the description or time of the task to update to.
     * @param field a String representing the field to update.
     */

    public void update(String str, String field) {
        switch (field) {
        case DESCRIPTION:
            super.update(str);
            break;
        case TIME:
            this.time = LocalDateTime.parse(str,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            break;
        default:
            break;
        }
    }

}
