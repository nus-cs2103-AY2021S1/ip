package seedu.duke.task;

import java.time.LocalDate;

import seedu.duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Task</code> with no additional details.
 */
public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    public static ToDo of(String command) throws InvalidCommandFormatException {
        if (command.length() <= 5) {
            throw new InvalidCommandFormatException("ToDo cannot be empty.");
        }
        String content = command.substring(5);
        return new ToDo(content);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String print() {
        return "T | " + super.print();
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return false;
    }
}
