package main.java.commands;
import main.java.tasklist.TaskList;
import main.java.storage.Storage;
import main.java.ui.Ui;

public abstract class Command {
    public Command(){
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
