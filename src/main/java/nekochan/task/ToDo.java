package nekochan.task;

import nekochan.exceptions.NekoStorageException;
import nekochan.exceptions.NekoTaskCreationException;

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
            throw new NekoTaskCreationException("I need something to work with.");
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
            throw new NekoStorageException("Something doesn't seem right...");
        }
        String[] content = code.split("\\|", 3);
        if (content.length != 3) {
            throw new NekoStorageException("There are some holes in my memory...");
        }
        ToDo newToDo = new ToDo(content[2]);
        if (content[1].equals("Y")) {
            newToDo.setCompleted();
        } else if (!content[1].equals("N")) {
            throw new NekoStorageException("There are some holes in my memory...");
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
                super.isCompleted ? "Y" : "N",
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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
