package main.java;

/**
 * AddCommands add tasks to the TaskList
 *
 * @author Lio
 */
public class AddCommand extends Command {
    String data;

    /**
     * Constructor
     *
     * @param data data form of the task to be added
     */
    public AddCommand(String data) {
        this.data = data;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task task = Task.fromData(data);
        tasks.add(task);
        ui.taskAdded(task, tasks);
    }
}
