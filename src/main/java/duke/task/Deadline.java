package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initializes Deadline object that has not completed task by default.
     *
     * @param description Description of the task.
     * @param by Date that task has to finished by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes Deadline object with specified task completion status.
     *
     * @param description Description of the task.
     * @param by Date that task has to finished by.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }
    public LocalDate getByWhen() {
        return this.by;
    }
    
    @Override
    public boolean checkIfDuplicate(Task otherTask) {
        if (otherTask instanceof Deadline) {
            // share same description and timing
            return super.checkIfDuplicate(otherTask) && by.equals(((Deadline) otherTask).getByWhen());
        }
        return false;
    }
    
    @Override
    public String toString() {
        String formattedTimeBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]" + super.toString() + " (by: %s)", formattedTimeBy);
    }

}
