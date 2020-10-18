import java.io.IOException;

/**
 * A done command to mark a given task number as done
 */
class DoneCommand extends Command {
    private final int doneTask;

    /**
     * Constructor for DoneCommand
     * @param task Index of task to be marked as done
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    DoneCommand(int task, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.doneTask = task;
    }

    /**
     * Executes the DoneCommand
     * @return Returns String confirmation of completed task
     * @throws IOException Exception thrown when updating storage file
     */
    @Override
    public String execute() throws IOException {
        tasks.done(this.doneTask);
        storage.saveFile(tasks);
        return ui.printf("Nice! I've marked this task as done:\n" + tasks.get(this.doneTask));
    }
}
