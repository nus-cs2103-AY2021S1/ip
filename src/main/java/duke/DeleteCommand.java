package duke;

/**
 * Delete task of the given index.
 */
public class DeleteCommand extends Command {
    private int idx;

    /**
     * DeleteCommand constructor.
     *
     * @param idx index of the task to be deleted.
     */
    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.deleteTask(this.idx);
        storage.writeToFile(taskList);
    }
}
