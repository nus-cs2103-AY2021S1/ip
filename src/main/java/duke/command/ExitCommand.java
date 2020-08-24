package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskException;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand(String args) {
        super(args);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        return "See you soon!";
    }
}
