package sparkles.command.addcommand;

import sparkles.SparklesException;
import sparkles.command.Command;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Parser;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represents an AddCommand.
 */
public class AddCommand extends Command {

    public AddCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command
     * Add the task to the TaskList and
     * local disk file.
     * Before that, parse the command to differentiate
     * between AddDeadline, AddEvent or AddTodo.
     *
     * @param taskList TaskList object containing list
     *                  of tasks
     * @param ui       Ui Object that interacts with user
     * @param storage  storage object dealing with
     *                  local disk file
     * @return response to the command
     * @throws SparklesException custom exception that handles
     *                           exception of Sparkles
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        Command c = Parser.parseAddCommand(command);
        return c.execute(taskList, ui, storage);
    }

    /**
     * Concatenate all lines of output together as one whole string
     * and return that string to be executed by the command
     * in the method "execute".
     *
     * @param ui       Ui object that deals with user interaction
     * @param task     Task whose string output is required
     * @param taskList TaskList object containing list of tasks
     * @return final response to the command
     */
    @Override
    public String getResponse(Ui ui, Task task, TaskList taskList) {
        String response;

        if (taskExist(taskList, task)) {
            response = respondDuplicate(ui);
        } else {
            ui.print("     Got it. I've added this task");

            response = "Got it. I've added this task\n";
            response += task.printTask().trim() + "\n";

            taskList.add(task);

            response += ui.showListSize(taskList.getListSize());
        }
        return response;
    }

    public boolean taskExist(TaskList taskList, Task task) {
        return taskList.hasTheSameTask(task);
    }

    public String respondDuplicate(Ui ui) {
        ui.print("     Task already exist! Please verify again.");
        return "Task already exist! Please verify again.";
    }
}
