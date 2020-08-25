package duke.command;

import duke.Storage;
import duke.TaskList;

public class Command {
    protected String command;
    protected TaskList list;
    protected Storage storage;

    public Command(String command, TaskList list, Storage storage) {
        this.command = command;
        this.list = list;
        this.storage = storage;
    }
    // empty command
    public Command() {
        this.command = null;
        this.list = null;
        this.storage = null;
    }

    public void execute(String command, TaskList list, Storage storage) {
    }

    public boolean isExit() {
        return false;
    }
}
