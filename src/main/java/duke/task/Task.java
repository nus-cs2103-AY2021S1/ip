package duke.task;

import duke.DukeException;
import duke.ExceptionTypeEnum;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        assert !description.isEmpty() : "Task description should not be empty";
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks whether a task's associated date is the same as the supplied date. Meant to be
     * overriden in inherited classes such as <code>DeadlineTask</code>.
     *
     * @param date a LocalDate to check for equality
     * @return false
     */
    public boolean isSameDate(LocalDate date) {
        return false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public void markAsDone() throws DukeException {
        if(this.isDone) {
           throw new DukeException(ExceptionTypeEnum.ITEM_ALREADY_DONE);
        }
        this.isDone = true;
    }


    public String toDBString() {
        return String.format("%s~%s", isDone ? 1 : 0, description);
    }
}
