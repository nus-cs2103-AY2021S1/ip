package seedu.duke.command;

import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.exception.InvalidCommandFormatException;

public class FindCommand implements Command {
    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException {
        if (command.length() <= 5) {
            throw new InvalidCommandFormatException("Please enter the keywords you are searching for.");
        }
        String keyword = command.substring(5);
        return taskList.find(keyword);
    }

    public boolean isDone() {
        return false;
    }
}
