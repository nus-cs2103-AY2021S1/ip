package duckie.command;

import java.lang.reflect.Array;
import java.util.ArrayList;

import duckie.task.*;
import duckie.exception.*;
import duckie.Ui;
import duckie.Storage;

/**
 * Command to mark a task as Done
 */
public class DoneCommand extends Command {
    private int ind;

    /**
     * Instantiate the DoneCommand
     * @param ind Index of the Task in TaskList to be marked done
     */
    public DoneCommand(int ind) {
        this.ind = ind;
    }

    /**
     * Mark a required Task as done
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

        if (lst.size() < ind) {
            throw new DuckieNoIndexException();
        }

        tasks.markTaskDone(this.ind - 1);
        Task task = tasks.getTaskList().get(this.ind - 1);
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        ui.checkTaskReply(task);
    }
}
