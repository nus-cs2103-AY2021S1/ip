package duke.task;

/*
    @author dianneloh9-reused
    Inspiration from how she created an interface to have tasks that have datetime to implement.
 */

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * An interface for tasks that has a datetime attribute.
 */
public interface TimeBased {
    /**
     * Returns the datetime associated to the task.
     *
     * @return the datetime of the task.
     */
    LocalDateTime getDateTime();
    /**
     * Returns the date associated to the task.
     *
     * @return the date of the task.
     */
    LocalDate getDate();
}
