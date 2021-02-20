
package duke.command;

import java.io.IOException;

import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.handle.TaskNotFoundException;

/**
 * The Command class represents a command that can be executed.
 *
 * The command class is an abstract class and the class has an abstract
 * method execute.
 */
public abstract class Command {

    /**
     * Takes in the task list, the interface, and the storage components, and execute
     * the command with these components.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the stroage process needs to be handled
     */
    public abstract Result excecute(TaskList taskList, Ui ui, Storage storage)
            throws TaskNotFoundException, IOException;

    /**
     * Determines whether the command is an exit command.
     *
     * @return The boolean value of whether the command is an exit command.
     */
    public boolean isContinuing() {
        return true;
    }
}