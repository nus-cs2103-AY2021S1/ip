package main.java;

/**
 * DeleteCommands delete tasks from the TaskList
 *
 * @author Lio
 */
public class DeleteCommand extends Command {
    int id;

    /**
     * Constructor
     *
     * @param id ID of the task to be deleted
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(id);
        ui.taskDeleted(task, tasks);
    }
}
