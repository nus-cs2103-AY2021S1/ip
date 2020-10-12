package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Task</code> that has to be done by certain date.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Deadline</code>
     * @param deadline the date on which the <code>Deadline</code> is due
     */
    public Deadline(String title, LocalDate deadline) {
        super(title);
        this.deadline = deadline;
    }

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Deadline</code>
     * @param isDone   whether or not the <code>Deadline</code> is marked as completed
     * @param deadline the date on which the <code>Deadline</code> is due
     */
    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String print() {
        return "D | " + super.print() + " | " + this.deadline;
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.deadline.equals(date);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline otherTask = (Deadline) obj;
            return super.equals(otherTask) && this.deadline.equals(otherTask.deadline);
        } else {
            return false;
        }
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task == this) {
            return true;
        } else if (task instanceof Deadline) {
            Deadline otherTask = (Deadline) task;
            return super.isDuplicate(otherTask);
        } else {
            return false;
        }
    }
}
