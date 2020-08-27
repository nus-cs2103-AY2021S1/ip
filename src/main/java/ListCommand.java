package main.java;

/**
 * ListCommands list out the current TaskList
 *
 * @author Lio
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
