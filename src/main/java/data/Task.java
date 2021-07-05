package data;

import java.time.LocalDate;

/**
 * Encapsulates a Task.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * If the task is done, a tick symbol will be returned.
     * Else, a cross will be returned.
     *
     * @return Status of task
     */

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Mark the task as done.
     */
    public void doTask() {
        this.isDone = true;
    }


    /**
     * Returns description of task.
     * @return description of task.
     */
    public String getDescription() {
        return description;
    }

    public void changeDate(LocalDate date){

    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }


}
