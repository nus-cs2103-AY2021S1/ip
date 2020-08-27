package duckie.command;

import duckie.task.*;
import duckie.exception.*;
import duckie.Ui;
import duckie.Storage;

/**
 * Command to list all the current tasks in the TaskList
 */
public class ListCommand extends Command {
    /**
     * List and display all the tasks in the TaskList currently
     * @param tasks TaskList containing all the tasks
     * @param ui Ui to interact with the users
     * @param storage Storage to write to File
     * @throws DuckieException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        if (tasks.getTaskList().size() == 0) {
            throw new DuckieNoListException();
        } else {
            ui.displayListReply(tasks.getTaskList());
        }
    }
}
