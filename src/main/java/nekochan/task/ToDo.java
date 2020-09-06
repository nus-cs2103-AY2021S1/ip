package nekochan.task;

import nekochan.exceptions.NekoStorageException;
import nekochan.exceptions.NekoTaskCreationException;
import nekochan.util.Messages;

/**
 * The {@code ToDo} class represents the most basic task with no additional parameters.
 * Extends the {@link Task} class.
 */
public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    /**
     * Returns a {@code ToDo} object with the specified {@code details}.
     *
     * @param details the description of the task.
     * @return a {@code ToDo} object with the specified details.
     * @throws NekoTaskCreationException if format of the specified details is not recognised.
     */
    public static ToDo createTask(String details) throws NekoTaskCreationException {
        if (details == null) {
            throw new NekoTaskCreationException(Messages.PARSE_COMMAND_TODO_MISSING_ARGUMENT);
        }
        return new ToDo(details);
    }

    /**
     * Decodes an encoded string into a {@code ToDo} object.
     *
     * @param code the encoded string.
     * @return a {@code ToDo} reconstructed from the encoded string.
     * @throws NekoStorageException if format of the code is incorrect.
     */
    public static ToDo decode(String code) throws NekoStorageException {
        if (code.charAt(0) != 'T') {
            throw new NekoStorageException(Messages.DECODE_UNEXPECTED_TYPE_ERROR);
        }
        String[] content = code.split("\\|", 3);
        if (content.length != 3) {
            throw new NekoStorageException(Messages.STORAGE_ERROR_CORRUPT);
        }
        ToDo newToDo = new ToDo(content[2]);
        if (content[1].equals(ENCODED_COMPLETE_FLAG)) {
            newToDo.setCompleted();
        } else if (!content[1].equals(ENCODED_INCOMPLETE_FLAG)) {
            throw new NekoStorageException(Messages.STORAGE_ERROR_CORRUPT);
        }
        return newToDo;
    }

    /**
     * Returns an encoded string representation of this {@code Deadline}.
     *
     * @return an encoded string representation of this {@code Deadline}.
     */
    @Override
    public String encode() {
        return String.format("T|%s|%s",
                super.isCompleted ? ENCODED_COMPLETE_FLAG : ENCODED_INCOMPLETE_FLAG,
                super.description);
    }

    /**
     * Returns a string representation of this {@code ToDo}.
     *
     * @return a string representation of this {@code ToDo}.
     */
    @Override
    public boolean match(String searchParameter) {
        return description.contains(searchParameter) || searchParameter.contains(description);
    }

    /**
     * Returns true if both {@code ToDo} are similar.
     */
    @Override
    boolean similar(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ToDo)) {
            return false;
        }

        ToDo other = (ToDo) obj;
        return other.description.toLowerCase().equals(description.toLowerCase());
    }

    /**
     * Returns true if both {@code ToDo} have the same identity or data.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ToDo)) {
            return false;
        }

        ToDo other = (ToDo) obj;
        return other.description.equals(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
