package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

import java.util.List;

/**
 * Represent a show list command.
 */
public class ShowListCommand extends Command {

    public ShowListCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Display user's tasks to the user.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws SparklesException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        taskList.updateList(storage.load());
        List<Task> tasks = taskList.getStorage();

        if (tasks.isEmpty()) {
            ui.print("     Tasks list is Empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                task.printTask(i + 1);
            }
        }
    }
}
