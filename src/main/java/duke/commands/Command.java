package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public abstract class Command {
    protected TaskList taskList;
    protected String command;
    protected Ui ui;
    protected Storage storage;
    protected boolean isExit = false;

    public Command(String command) {
        this.command = command;
    }

    public void init(TaskList taskList, Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public boolean getExitStatus() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
