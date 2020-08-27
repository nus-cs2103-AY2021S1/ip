package alice.task;

import alice.AliceException;

/**
 * Represents a task to be done by the user.
 */
public class Todo extends Task {
    /**
     * Creates an undone task to be done.
     *
     * @param description describes the task to be done.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo task with the specified completion status and description.
     *
     * @param isDone the completion status of the task, true if completed; false otherwise.
     * @param description describes the task to be done.
     */
    private Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Decode an encoded string representation of the todo task.
     *
     * @param saved the string representation of the encoded task.
     * @return the <code>Todo</code> task described in the string representation.
     * @throws AliceException if the encoded string is corrupted.
     */
    public static Todo decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 2) {
            return new Todo(isDone, inputs[1]);
        } else {
            throw new AliceException("Corrupted Todo data");
        }
    }

    @Override
    public String encode() {
        return "T | " + super.encode();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
