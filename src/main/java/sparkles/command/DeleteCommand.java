package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represent a delete command.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Remove the specific task that user wants to delete
     * from the local disk as well as the TaskList object.
     * Printing out necessary information with regards to this
     * execution.
     *
     * @param taskList TaskList object containing list
     *                  of tasks
     * @param ui       Ui Object that interacts with user
     * @param storage  Storage object dealing with
     *                  local disk file
     * @return response to the command
     * @throws SparklesException that handles exception of Sparkles
     *                           such as StringIndexOutOfBoundsException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        int index;
        String response;

        try {
            index = Integer.parseInt(command.substring(7));
            Task task = taskList.getStorage().get(index - 1);

            response = this.getResponse(ui, task, taskList);
            return response;
        } catch (Exception ex) {
            if (ex instanceof StringIndexOutOfBoundsException) {
                throw new SparklesException("     OOPS!! Task in the list to be deleted is not specified!");
            } else if (taskList.getStorage().isEmpty()) {
                throw new SparklesException("     OOPS!! Task list is empty!");
            } else {
                throw new SparklesException("     OOPS!! Task does not exist!");
            }
        } finally {
            storage.updateFile(taskList.getStorage());
        }
    }

    @Override
    public String getResponse(Ui ui, Task task, TaskList taskList) {
        String response;

        ui.print("     Noted, I have removed this task:");

        response = "Noted, I have removed this task:\n";
        response += task.printTask().trim() + "\n";

        taskList.remove(task);

        response += ui.showListSize(taskList.getListSize());
        return response;
    }
}
