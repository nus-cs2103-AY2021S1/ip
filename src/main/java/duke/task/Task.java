package duke.task;

import java.time.LocalDate;

/**
 * Class handling tasks of different types (i.e. its subclasses)
 *
 */
public class Task {
    protected String description;
    protected String identifier;
    protected boolean isDone;
    protected LocalDate date;

    /**
     * constructor to describe state and specifics of Task
     *
     * @param description to describe task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * overridden methods for its subclasses to help write the tasks to duke.txt
     *
     * @return
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * helper function to determine whether to use the tick or cross symbol for printing of tasks
     *
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * getter for isDone boolean
     *
     * @return
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * setter for isDone boolean
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * getter function for String description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    public LocalDate getLocalDate() {

        assert !(this instanceof Todo) : "Todos have no dates!";
        return date;
    }

    /**
     * toString method for printing
     *
     * @return
     */
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}
