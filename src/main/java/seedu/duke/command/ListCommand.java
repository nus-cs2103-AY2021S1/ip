package seedu.duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import seedu.duke.DukeException;
import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;

/**
 * Represents a <code>Command</code> telling Duke to list <code>Task</code>s.
 */
public class ListCommand implements Command {
    private String[] command;

    public ListCommand(String[] command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws DukeException {
        if (command.length == 1) {
            return taskList.showList();
        } else if (command.length == 2) {
            try {
                LocalDate date = LocalDate.parse(command[1]);
                return taskList.showList(date);
            } catch (DateTimeException e) {
                throw new DukeException("Please provide date in yyyy-mm-dd format.");
            }
        } else {
            throw new DukeException("Wrong format.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
