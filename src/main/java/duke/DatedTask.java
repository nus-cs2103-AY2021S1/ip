package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles addition of date-based Tasks.
 */

public abstract class DatedTask extends Task {
    /** Date of task */
    protected LocalDate date;

    /**
     * Constructor for Dated Tasks.
     * @param name Description of duke.Task.
     * @param date Date of duke.Task.
     */
    public DatedTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructor for Dated Task with tags.
     * @param name Description of duke.Task.
     * @param tags Tags associated with Task.
     */
    public DatedTask(String name, LocalDate date, String[] tags) {
        super(name);
        this.date = date;
        this.tags = tags;
    }

    /**
     * Constructor for Dated Tasks with completed state.
     * @param name Description of duke.Task.
     * @param isCompleted State of completion of duke.Task.
     * @param date Date of duke.Task.
     */
    public DatedTask(String name, boolean isCompleted, LocalDate date) {
        super(name, isCompleted);
        this.date = date;
    }

    /**
     * Constructor for Dated Tasks with completed state and tags.
     * @param name Description of duke.Task.
     * @param isCompleted State of completion of duke.Task.
     * @param date Date of duke.Task.
     */
    public DatedTask(String name, boolean isCompleted, LocalDate date, String[] tags) {
        super(name, isCompleted, tags);
        this.date = date;
    }

    /**
     * Represents duke.Task in String form.
     * @return String representation of duke.Task object.
     */
    @Override
    public String format() {
        return super.format() + getDelimiter() + this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
