package duke.command;

import duke.Storage;
import duke.taskListHandler;

public abstract class Command {

    public abstract void execute(taskListHandler handler, Storage storage);

}
