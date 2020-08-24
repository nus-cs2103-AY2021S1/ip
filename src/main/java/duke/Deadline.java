package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

/**
 * Represents a task with info of by when it must be done
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected Optional<LocalTime> time;

    /**
     * Initializes a newly created Deadline with a description, date, and whether it is done.
     * @param desc description of task.
     * @param date date of task.
     * @param isDone whether task is done.
     */
    public Deadline(String desc, LocalDate date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
        this.time = Optional.empty();
    }

    /**
     * Initializes a newly created Deadline with a description, date, time, and whether it is done.
     * @param desc description of task.
     * @param date date of task.
     * @param time time of task.
     * @param isDone whether task is done.
     */
    public Deadline(String desc, LocalDate date, LocalTime time, boolean isDone) {
        super(desc, isDone);
        this.date = date;
        this.time = Optional.ofNullable(time);
    }

    /**
     * Sets this task as done.
     * @return task set as done.
     */
    @Override
    public Task setDone() {
        Task doneTask = this.time.map(localTime -> new Deadline(this.desc, this.date, localTime, this.isDone))
                .orElseGet(() -> new Deadline(this.desc, this.date, this.isDone));
        doneTask.isDone = true;
        return doneTask;
    }

    /**
     * Formats the task to a string to write in a file.
     * @return formatted task.
     */
    @Override
    public String formatTask() {
        return ("D" + " | " + (isDone ? "V" : "X") + " | " + desc + " | "
                + this.date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " "
                + this.time.map(localTime -> localTime.format(DateTimeFormatter.ofPattern("HHmm")))
                .orElse(""));
    }

    /**
     * Returns a string representation of the task.
     * @return string representation of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + (this.time.isPresent() ? this.time.get() : "") + ")";
    }
}
