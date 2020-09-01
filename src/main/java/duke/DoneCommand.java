package duke;

/**
 * Marks the task of the given index to be done.
 */
public class DoneCommand extends Command {
    private int idx;

    /**
     * DoneCommand constructor.
     *
     * @param idx index of the task to be marked as done.
     */
    public DoneCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.markTaskAsDone(idx, ui);
        storage.writeToFile(taskList);
    }
}
