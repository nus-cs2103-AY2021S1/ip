package duke.command;

import duke.exception.DukeException;
import duke.exception.NoTaskException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the list command.
 * When executed, the current list will be displayed.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand. Executing this command will
     * print out every details of the current task such as
     * the type (todo, deadline, or event), status (done or not),
     * and the task's name as well as the date for event and deadline
     * task.
     *
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     * @throws DukeException If TaskList is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new NoTaskException();
        } else {
            String message = ui.showList(tasks);
            ui.sendMessage(message);
        }
    }
}
