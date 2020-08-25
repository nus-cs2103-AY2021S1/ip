package duke.command;

import duke.exception.InvalidIndexException;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Is responsible for handling commands starting with <code>delete</code>.
 */
public class DeleteCommand extends Command {
    public final static String COMMAND = "delete";
    private int index;

    /**
     * Creates a <code>DeleteCommand</code> object.
     * @param string The position of the task being deleted
     * @throws InvalidIndexException If cannot parse the string into integer
     */
    public DeleteCommand(String string) throws InvalidIndexException {
        try {
            index = Integer.parseInt(string) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Deletes the task in the specified position.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     * @throws InvalidIndexException If that position is out of the range of the <code>TaskList</code>
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        try {
            ui.showDeleteTask(tasks.remove(index), tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}
