package commands;

import storage.DataStorage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    protected String command;
    protected String item;

    public Command(String command, String item) {
        this.command = command;
        this.item = item;
    }

    public abstract void execute(TaskList taskList, Ui u, DataStorage ds);
    public abstract boolean isExit();
}
