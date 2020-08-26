package duke;

public class DoneCommand extends Command {
    int idx;

    /**
     * Instantiates DoneCommand.
     *
     * @param idx Index of completed task.
     */
    DoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes command by marking completion for task in
     * TaskList and save data to storage.
     *
     * @param tasks   TaskList containing the tasks.
     * @param storage To read and write to file.
     * @param ui      Interact with user.
     * @throws DukeException If input does not meet requirements.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task task = tasks.markAsDone(idx);
        ui.showTaskDone(task);
        storage.saveTasksToFile(tasks);
    }
}
