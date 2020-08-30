package duke.task;

import duke.exception.EmptyTaskException;

public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo object representing the given command.
     *
     * @param task Command describing the ToDo to be created.
     * @return ToDo object representing the task description.
     * @throws EmptyTaskException If no text is provided after "todo ".
     */
    public static ToDo create(String task) throws EmptyTaskException {
        if (task.length() <= 5) throw new EmptyTaskException("todo");
        return new ToDo(task.substring(5));
    }

    /**
     * Creates a new ToDo object represented by its String when read from a file.
     *
     * @param description Description of task.
     * @return Event object representing the given details.
     */
    public static ToDo createFromFile(String description) {
        return new ToDo(description);
    }

    @Override
    public String print() {
        return "T | "
                + (isDone ? 1 : 0) + " | "
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ToDo) {
            return description.equals(((ToDo) obj).description);
        } else {
            return false;
        }
    }
}
