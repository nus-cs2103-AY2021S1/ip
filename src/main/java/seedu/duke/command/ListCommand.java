package seedu.duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Command</code> telling Duke to list <code>Task</code>s.
 */
public class ListCommand implements Command {
    private String[] command;

    public ListCommand(String[] command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException {
        if (command.length == 1) {
            return taskList.showList();
        } else if (command.length == 2) {
            try {
                LocalDate date = LocalDate.parse(command[1]);
                return taskList.showList(date);
            } catch (DateTimeParseException e) {
                throw new InvalidCommandFormatException("Please enter a valid date in the yyyy-mm-dd format.");
            }
        } else {
            throw new InvalidCommandFormatException("Wrong format for list command.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
