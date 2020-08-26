package duke.task;

import duke.exception.DukeException;

/**
 * This class is a ToDo type of task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param desc The description of this task.
     */
    public ToDo(String desc) {
        super(desc);
        if (this.desc.isBlank())
            throw new DukeException("The description cannot be empty");
    }

    /**
     * {@inheritDoc}
     */
    public String getSaveToFileString() {
        return "T`" + super.getSaveToFileString();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
