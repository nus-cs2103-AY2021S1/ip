package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import duke.exception.DukeException;

/**
 * Represents a general task that has a name and status of completion.
 * More specific task will inherit from this class.
 */
public abstract class Task {
    protected String name;
    protected boolean status;

    /**
     * Represents the most basic task.
     * @param name the description of the task
     */
    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public boolean isDone() {
        return this.status;
    }

    public void markAsDone() {
        this.status = true;
    }

    public String toString() {
        return "[" + (status ? "\u2713" : "\u2718") + "] " + this.name.trim();
    }

    public String getName() {
        return this.name.trim();
    }

    public abstract String convertTxt();

    public abstract String getDateAsString();

    public abstract Optional<LocalDate> getDate();

    public abstract Optional<LocalTime> getTime();

    public abstract void update(String time) throws DukeException;

}
