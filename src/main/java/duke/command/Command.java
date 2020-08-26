package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

import java.io.IOException;

public abstract class Command {

    /**
     * Abstract class that is inherited by all command classes.
     */
    
    public abstract void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException;
    
}
