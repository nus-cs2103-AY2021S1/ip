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

        String response = "";

        if (taskList.isEmpty()) {
            ui.print("     Tasks list is Empty.");

            return "Tasks list is Empty.";
        }

        response = this.getResponse(ui, null, taskList);
        return response;
    }

    @Override
    public String getResponse(Ui ui, Task task, TaskList taskList) {
        assert task == null;

        String response = "";
        List<Task> listFromTask = taskList.getStorage();

        for (int i = 0; i < listFromTask.size(); i++) {
            Task t = listFromTask.get(i);
            response += t.printTask(i + 1).trim() + "\n";
        }

        return response;
    }
}
