package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.Message;
import duke.Storage;
import duke.TaskList;
import duke.exception.InvalidCommandFormatException;

/**
 * Represents a <code>Command</code> telling Duke to list <code>Task</code>s.
 */
public class ListCommand implements Command {
    private String[] command;

    public ListCommand(String[] command) {
        this.command = command;
    }

    @Override
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

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ListCommand) {
            return Arrays.equals(this.command, ((ListCommand) obj).command);
        } else {
            return false;
        }
    }
}
