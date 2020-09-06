package dev.jingyen.duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A Deadline is a Task, with the addition of a deadline, signifying when it has to be completed before.
 *
 * @author jingyenloh
 */
public class Deadline extends Task {
    private static final String SAVE_STRING = "DEADLINE|%s|%s|%s";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    private final LocalDate deadline;

    /**
     * A constructor for a dev.jingyen.duke.model.Deadline dev.jingyen.duke.model.Task that is not done yet.
     *
     * @param taskName the name of the dev.jingyen.duke.model.Task
     * @param deadline when the dev.jingyen.duke.model.Task has to be completed before
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * A constructor for a Deadline Task, which may or may not be done yet.
     *
     * @param isDone   whether the dev.jingyen.duke.model.Task has been completed
     * @param taskName the name of the dev.jingyen.duke.model.Task
     * @param deadline when the dev.jingyen.duke.model.Task has to be completed before
     */
    public Deadline(boolean isDone, String taskName, LocalDate deadline) {
        super(isDone, taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                deadline.format(DATE_FORMAT));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deadline d = (Deadline) o;
        return super.equals(d) && deadline.equals(d.deadline);
    }

    @Override
    public String toSaveString() {
        return String.format(SAVE_STRING, super.isDone, super.taskName, deadline);
    }
}
