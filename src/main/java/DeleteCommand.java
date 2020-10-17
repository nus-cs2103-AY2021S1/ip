import java.io.IOException;

/**
 * A delete command to delete a given task number
 */
class DeleteCommand extends Command {
    private final int deleteTask;

    /**
     * Constructor for DeleteCommand
     * @param task Index of task to be deleted
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    DeleteCommand(int task, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.deleteTask = task;
    }

    /**
     * Executes the DeleteCommand
     * @return Returns String confirmation of deleted task
     * @throws IOException Exception thrown when updating storage file
     */
    @Override
    public String execute() throws IOException {
        //retrieves the string representation of the task before it is deleted
        String taskString = this.tasks.get(this.deleteTask);
        this.tasks.delete(this.deleteTask);
        this.storage.saveFile(this.tasks);
        return this.ui.printf("Noted. I've removed this task:\n" + taskString + "\n" + this.tasks.taskCount());
    }
}
