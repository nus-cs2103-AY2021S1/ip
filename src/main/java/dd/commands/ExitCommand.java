package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

/**
 * An exit command prints out a exit greeting and
 * saves the task list data into a file.
 */
public class ExitCommand extends Command {

    /**
     * Class Constructor.
     *
     * @param command Command given.
     * @param item Empty string.
     */
    public ExitCommand(String command, String item) {
        super(command, item);
    }

    /**
     * Calls the exit greeting and saves the current data.
     *
     * @param tasks Current TaskList to modify.
     * @param u Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @return Exit greeting string from DD.
     * @throws DukeException If no data is able to be written.
     */
    @Override
    public String execute(TaskList tasks, Ui u, DataStorage ds) throws DukeException {
        String output = u.exit() + "\n" + ds.writeData(tasks.getTaskList());
        return output;
    }
}
