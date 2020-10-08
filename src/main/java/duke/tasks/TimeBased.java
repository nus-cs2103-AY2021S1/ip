package duke.tasks;

import duke.utils.DukeDateTime;

/**
 * An interface for tasks with a date and/or time.
 */
public interface TimeBased {

    /**
     * Gets the date and/or time associated to this task.
     *
     * @return The date and/or time.
     */
    DukeDateTime getTime();

}
