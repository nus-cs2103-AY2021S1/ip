package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList list) throws DukeException;

    public boolean isExit(){
        return false;
    }
}
