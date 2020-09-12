import java.io.IOException;

/**
 * A delete command to delete a given task number
 */
class DeleteCommand extends Command {
    private int deleteTask;

    DeleteCommand(int task, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.deleteTask = task;
    }

    @Override
    public String execute() throws IOException {
        //retrieves the string representation of the task before it is deleted
        String taskString = this.tasks.get(this.deleteTask);
        this.tasks.delete(this.deleteTask);
        this.storage.saveFile(this.tasks);
        return this.ui.printf("Noted. I've removed this task:\n" + taskString + "\n" + this.tasks.taskCount());
    }
}
