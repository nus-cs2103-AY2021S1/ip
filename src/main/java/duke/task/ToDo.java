package duke.task;

import java.time.LocalDate;

/** A task of type ToDo. */
public class ToDo extends Task {

    /**
     * Constructs a @ToDo.
     *
     * @param task The description of the @ToDo.
     * @param isDone The completion status of the @ToDo.
     */
    private ToDo (String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Constructs an uncompleted @ToDo.
     *
     * @param task The task description of the @ToDo.
     */
    public ToDo(String task) {
        super(task);
    }

    /** Marks the @ToDo as done. */
    @Override
    public ToDo markDone() {
        return new ToDo(task, true);
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * Compares with another object.
     *
     * @param o The object compared.
     * @return True if the object compared is a @ToDo with the same task description.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ToDo && super.equals(o);
    }

    /**
     * The format used for saving.
     * @return The String format used for saving.
     */
    @Override
    public String saveFormat() {
        return "T" + super.saveFormat();
    }

    /**
     * The format used to display on a list.
     * @return The String format of a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
