package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String keyword;
        try {
            keyword = input.split("find")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\tPlease enter a keyword you wish to find!");
        }
        if (keyword.isBlank()) {
            throw new DukeException("\tPlease enter a keyword you wish to find!");
        }
        taskList.findTasks(keyword.trim());
    }
}
