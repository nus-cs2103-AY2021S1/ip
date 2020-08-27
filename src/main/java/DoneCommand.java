package main.java;

/**
 * DoneCommands mark tasks in the TaskList as done
 *
 * @author Lio
 */
public class DoneCommand extends Command {
    int id;

    /**
     * Constructor
     *
     * @param id ID of the task to be marked as done
     */
    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.setDone(id);
        ui.taskDone(task);
    }
}
