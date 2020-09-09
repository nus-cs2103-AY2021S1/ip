package duke.commands;

import duke.Storage;
import duke.TaskList;

public abstract class Command {

    public abstract String run(TaskList taskList, Storage storage);
    public boolean isBye() {
        return false;
    }

}
