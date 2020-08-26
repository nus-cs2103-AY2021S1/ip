package duke.task;

import duke.exceptions.DukeStorageException;
import duke.exceptions.DukeTaskCreationException;

/**
 * The {@code ToDo} class represents the most basic task with no additional parameters.
 * Extends the {@link Task} class.
 */
public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    /**
     * Returns a {@code ToDo} object with the specified details.
     *
     * @param details the description of the task.
     * @return a {@code ToDo} object with the specified details.
     * @throws DukeTaskCreationException if format of the specified details is not recognised.
     */
    public static ToDo createTask(String details) throws DukeTaskCreationException {
        if (details == null) {
            throw new DukeTaskCreationException("I need something to work with.");
        }
        return new ToDo(details);
    }

    /**
     * Decodes an encoded string into a {@code ToDo} object.
     *
     * @param code the encoded string.
     * @return a {@code ToDo} reconstructed from the encoded string.
     * @throws DukeStorageException if format of the code is incorrect.
     */
    public static ToDo decode(String code) throws DukeStorageException {
        if (code.charAt(0) == 'T') {
            String[] content = code.split("\\|", 3);
            if (content.length != 3) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            ToDo newToDo = new ToDo(content[2]);
            if (content[1].equals("Y")) {
                newToDo.setCompleted();
            } else if (!content[1].equals("N")) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            return newToDo;
        } else {
            throw new DukeStorageException("Something doesn't seem right...");
        }
    }

    /**
     * Returns an encoded string representation of this {@code Deadline}.
     *
     * @return an encoded string representation of this {@code Deadline}.
     */
    @Override
    public String encode() {
        return String.format("T|%s|%s",
                super.completed ? "Y" : "N",
                super.description);
    }

    /**
     * Returns a string representation of this {@code ToDo}.
     *
     * @return a string representation of this {@code ToDo}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
