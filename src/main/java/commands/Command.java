package main.java.commands;
import main.java.tasklist.TaskList;
import main.java.storage.Storage;
import main.java.ui.Ui;

/**
 * A command object containing information parsed from an Ui object
 * When the command is executed, the data structure will be modified and relevant message will be displayed.
 */
public abstract class Command {
    public Command(){
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
