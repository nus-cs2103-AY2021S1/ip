package duke.task;

import duke.exception.UnreadableSaveTaskException;

/**
 * Class representing a generic task with no dates attached.
 */
public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo object representing the given command.
     *
     * @param task TaskListOperator describing the ToDo to be created.
     * @return ToDo object representing the task description.
     */
    public static ToDo create(String task) {
        return new ToDo(task);
    }

    /**
     * Creates a new ToDo object represented by its String when read from a file.
     * The read string, when split with the '/' regex, must produce an array of length
     * 3 in the form <code>[E, {isDone indicator}, {description}]</code>.
     *
     * @param data Description of task.
     * @return Event object representing the given details.
     * @throws UnreadableSaveTaskException If data does not have length 3.
     */
    public static ToDo createFromFile(String[] data)
            throws UnreadableSaveTaskException {
        if (data.length != 3) {
            throw new UnreadableSaveTaskException();
        }

        return new ToDo(data[2]);
    }

    @Override
    public String toDataString() {
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
