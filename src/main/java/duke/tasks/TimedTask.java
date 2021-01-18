package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Intermediate class to define Tasks with a deadline or timing.
 */
public class TimedTask extends Task {

    LocalDate ddl;

    /**
     * Creates a TimedTask with the given task name and deadline/timing.
     *
     * @param task Task name
     * @param ddl deadline/timing
     */
    public TimedTask(String task, LocalDate ddl) {
        super(task);
        this.ddl = ddl;
    }

    /**
     * Returns deadline/timing in string format
     *
     * @return string format of deadline/timing
     */
    protected String getDateTime() {
        return ddl.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
