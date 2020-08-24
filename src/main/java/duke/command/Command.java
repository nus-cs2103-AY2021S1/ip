package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskException;
import duke.task.TaskList;

public abstract class Command {

    protected final String args;

    protected Command(String args) {
        this.args = args;
    }

    public boolean isExit() {
        return false;
    }

    public abstract String execute(TaskList taskList, Storage storage)
            throws TaskException, StorageException;
}
