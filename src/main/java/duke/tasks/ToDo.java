package duke.tasks;

/** Represents a toDo task. */
public class ToDo extends Task {

    /** Constructor.
     *
     * @param name The description of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /** Constructor.
     *
     * @param isDone The indicator of whether the task is done.
     * @param name The description of the task.
     */
    public ToDo(boolean isDone, String name) { super(isDone, name); }

    /** Returns the string representation of the task. */
    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
