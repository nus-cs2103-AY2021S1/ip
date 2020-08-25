package duke.commands;

import duke.storage.Storage;
import duke.utils.Ui;

import duke.task.TaskManager;

public abstract class Command {
    public abstract void executeCommand(TaskManager taskManger, Ui formatter, Storage storage);

    public boolean isExit(){
        return false;
    }
}
