/**
 * DoneCommand is a request to mark a Task as done.
 */

public class DoneCommand extends Command {

    private final int idx;

    public DoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks a task from the TaskList as done, updates storage about finished task.
     *
     * @param tasks   TaskList to be modified.
     * @param storage Storage to be updated.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException, TaskException {
        tasks.markAsDone(idx);
        storage.saveList(tasks);
        return Ui.getDoneTask(tasks.getTask(idx));
    }
}

