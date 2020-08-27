package duckie.command;

import java.util.ArrayList;

import duckie.task.*;
import duckie.Ui;
import duckie.Storage;
import duckie.exception.*;

/**
 * Command to delete all tasks in the TaskList
 */
public class DeleteAllCommand extends Command {
    /**
     * Delete all Tasks in TaskList
     * @param tasks TaskList containing all the tasks
     * @param ui Ui to interact with the users
     * @param storage Storage to write to File
     * @throws DuckieException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        ArrayList<Task> lst = tasks.getTaskList();
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        tasks.deleteAllTasks();
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        ui.deleteAllReply();
    }
}
