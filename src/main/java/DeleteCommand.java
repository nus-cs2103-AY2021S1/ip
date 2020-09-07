import java.io.IOException;

/**
 * A delete command to delete a given task number
 */
class DeleteCommand extends Command {
    private int deleteTask;

    DeleteCommand(int task) {
        super();
        this.deleteTask = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        //retrieves the string representation of the task before it is deleted
        String taskString = tasks.get(this.deleteTask);
        tasks.delete(this.deleteTask);
        storage.saveFile(tasks);
        return ui.printf("Noted. I've removed this task:\n" + taskString + "\n" + tasks.taskCount());
    }
}
