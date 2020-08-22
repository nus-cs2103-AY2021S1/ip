package duke.Command;

import duke.Storage;

import duke.Exception.DukeException;

import duke.Task.TaskList;

import duke.Ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
