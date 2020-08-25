package duke.command;

import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;

/**
 * Executable command that handles manipulation of Tasks between TaskList, Ui and Storage.
 * Sub-classes have to implement the execute command that dictates what movement is performed.
 * Commands can also be adjusted to terminate the overall Duke program by adjusting the isExit() method.
 */
public abstract class Command {

    /**
     * Returns a boolean to tell the program if it should exit.
     * @return true if the program should exit after executing, false otherwise
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes some manipulation of Task between taskList, ui and storage.
     * @param taskList List of Tasks to work with
     * @param ui UI element to be used
     * @param storage Storage element to be used
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);


}
