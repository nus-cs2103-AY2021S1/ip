package sparkles.command;

import java.util.List;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

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
     * @param taskList TaskList object containing list
     *                  of tasks
     * @param ui       Ui Object that interacts with user
     * @param storage  storage object dealing with
     *                  local disk file
     * @return response to the command
     * @throws SparklesException that handles exception of Sparkles
     *                           related to storage.load()
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        taskList.updateList(storage.load());
        List<Task> tasks = taskList.getStorage();
        String response = "";

        if (tasks.isEmpty()) {

            ui.print("     Tasks list is Empty.");
            response = "Tasks list is Empty.";

        } else {

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                response += task.printTask(i + 1).trim() + "\n";
            }
        }
        return response;
    }
}
