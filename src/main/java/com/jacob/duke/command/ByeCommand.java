package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;


public class ByeCommand implements Command {
    /**
     * Execution command for pre-determined ByeCommand
     * @param ui UI object to deal with program output
     * @param tasks Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     */
    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        return ui.sayBye();
    }

    /**
     * Check if it is the bye Command
     * @return true since it is
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
