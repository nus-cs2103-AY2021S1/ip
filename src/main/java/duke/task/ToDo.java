package duke.task;

import java.time.LocalDate;

import duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Task</code> with no additional details.
 */
public class ToDo extends Task {
    /**
     * Class constructor.
     *
     * @param title the content of the <code>ToDo</code>
     */
    public ToDo(String title) {
        super(title);
    }

    /**
     * Class constructor.
     *
     * @param title  the content of the <code>ToDo</code>
     * @param isDone whether or not the <code>ToDo</code> is marked as completed
     */
    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Creates a new ToDo from the user's input.
     *
     * @param command the user's input
     * @return the <code>ToDo</code> created
     * @throws InvalidCommandFormatException if the format of the user's input does not follow "todo [content]"
     */
    public static ToDo of(String command) throws InvalidCommandFormatException {
        if (command.length() <= 5) {
            throw new InvalidCommandFormatException("ToDo cannot be empty.");
        }
        String content = command.substring(5).trim();
        return new ToDo(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String print() {
        return "T | " + super.print();
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo otherTask = (ToDo) obj;
            return super.equals(otherTask);
        } else {
            return false;
        }
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task == this) {
            return true;
        } else if (task instanceof ToDo) {
            ToDo otherTask = (ToDo) task;
            return super.isDuplicate(otherTask);
        } else {
            return false;
        }
    }
}
