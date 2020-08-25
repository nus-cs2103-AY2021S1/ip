package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Is responsible for handing commands starting with <code>bye</code>.
 */
public class ExitCommand extends Command{
    public final static String COMMAND = "bye";
    
    /**
     * Creates an <code>ExitCommand</code> object.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Stops the program.
     * @return An indicator that helps the program know when to stop
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Copies tasks from temporary TaskList to local storage, then say goodbye.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.write(tasks);
        ui.showGoodbye();
    }
}
