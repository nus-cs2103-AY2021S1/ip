package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;

public abstract class Command {

    public abstract void execute(Ui ui, TaskManager taskManager, SaveManager saveManager);
    public abstract boolean isByeCommand();
    public String toString() {
        return this.getClass().getName();
    }

}
