import java.util.ArrayList;

/**
 * DeleteCommand is a request to delete a Task.
 */

public class DeleteCommand extends Command {

    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Deletes a task from the TaskList updates storage with deleted task.
     *
     * @param tasks   TaskList to be modified.
     * @param storage Storage to be updated.
     * @throws DukeException
     * @return ArrayList containing response message from Duke.
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Storage storage) throws DukeException {
        Task deletedTask = tasks.removeTask(idx);
        storage.saveList(tasks);
        return Ui.getDeletedTask(deletedTask, tasks);
    }

    public static String toInputString() {
        return "delete";
    }
}
