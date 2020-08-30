package duke.command;

import duke.Storage;

import duke.task.TaskList;

import duke.exception.DukeException;

public abstract class Command {
    public abstract void execute(TaskList list, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
