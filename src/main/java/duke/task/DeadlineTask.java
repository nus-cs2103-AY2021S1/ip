package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate by;
    static final String TASK_TYPE = "D";

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Checks whether a given date is the same as the event date
     *
     * @param date a LocalDate to check for equality
     * @return true if the event date "at" is the same as the date of the argument, false otherwise
     */
    public boolean isSameDate(LocalDate date) {
        return by.equals(date);
    }

    /**
     * Converts task to string - task type, tick/check and description
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", TASK_TYPE, getStatusIcon(), description,
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Converts task to string representation in database- task type, 1 for done/0 for not done, and description
     */
    public String toDBString() {
        return TASK_TYPE + "~" + super.toDBString() + "~" + by.toString();
    }
}
