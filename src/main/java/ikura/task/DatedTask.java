// DatedTask.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.time.LocalDate;

/**
 * An abstract base class for tasks that have a date component.
 */
public class DatedTask extends Task {

    private LocalDate date;

    /**
     * Constructs a new DatedTask with a title, description, and date.
     *
     * @param title the task title
     * @param description the task description
     * @param date the task date
     */
    protected DatedTask(String title, String description, LocalDate date) {
        super(title, description);
        this.date = date;
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * Set the date of this task
     *
     * @param date the new date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get the date of this task
     *
     * @return the date of this task
     */
    public LocalDate getDate() {
        return this.date;
    }
}
