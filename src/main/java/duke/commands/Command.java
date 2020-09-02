package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public final String description;

    Command(String description){
        this.description = description;
    }

    public abstract boolean isComplete();

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
