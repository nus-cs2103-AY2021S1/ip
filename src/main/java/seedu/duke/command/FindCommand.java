package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class FindCommand implements Command {
    String command;

    public FindCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("Find cannot be empty.");
        }
        String keyword = command.substring(5);
        taskList.find(keyword, ui);
    }

    public boolean isDone() {
        return false;
    }
}
