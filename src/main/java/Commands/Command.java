package Commands;

import Storage.Storage;
import Ui.Ui;
import task.TaskList;

public abstract class Command {
    protected TaskList taskList;
    protected String command;
    protected Ui ui;
    protected Storage storage;
    public boolean isExit = false;

    public Command(String command) {
        this.command = command;
    }

    public void init(TaskList taskList, Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
