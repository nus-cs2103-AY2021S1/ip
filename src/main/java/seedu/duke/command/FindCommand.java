package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;

public class FindCommand implements Command {
    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("Find cannot be empty.");
        }
        String keyword = command.substring(5);
        return taskList.find(keyword);
    }

    public boolean isDone() {
        return false;
    }
}
