package main.java.duke.command;

import main.java.duke.core.*;
import main.java.duke.task.*;
import main.java.duke.handle.*;

import java.io.IOException;

/**
 * The ExitCommand class represents a command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Takes in the task list, the interface, and the storage components, and exits
     * the program.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     */
    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Returns the boolean value to indicates that this is an exit command.
     *
     * @return The boolean value of whether this command is an exit command.
     */
    @Override
    public boolean isContinuing() {
        return false;
    }
}
