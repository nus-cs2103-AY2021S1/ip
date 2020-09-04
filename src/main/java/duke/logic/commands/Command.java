package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
