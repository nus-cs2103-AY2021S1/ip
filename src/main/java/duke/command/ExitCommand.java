package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(String input, TaskList taskList, Storage storage) {
        return;
    }
}
