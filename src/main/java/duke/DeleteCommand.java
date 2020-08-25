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

    /**
     * Execute the command.
     *
     * @param taskList the tasklist used for the command.
     * @param storage  the storage used for the command.
     * @throws DukeException duke failed to complete the command.
     */
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.deleteTask(this.idx);
        storage.writeToFile(taskList);
    }
}
