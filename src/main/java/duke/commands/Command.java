package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;

public abstract class Command {

    public abstract String run(TaskList taskList, Storage storage);
    public boolean isBye() {
        return false;
    }

}
