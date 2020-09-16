package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represent a done command.
 */
public class DoneCommand extends Command {

    public DoneCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Mark a task that user specify as done.
     * Updates the TaskList object and local disk file.
     * Printing out necessary information with regards to this
     * execution.
     *
     * @param taskList TaskList object containing list
     *                  of tasks
     * @param ui       Ui Object that interacts with user
     * @param storage  storage object dealing with
     *                  local disk file
     * @return response to the command
     * @throws SparklesException that handles exception of Sparkles
     *                           such as StringIndexOutOfBoundsException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        String response;

        try {
            response = this.getResponse(ui, null , taskList);
            return response;

        } catch (Exception ex) {
            if (ex instanceof StringIndexOutOfBoundsException) {
                throw new SparklesException("     OOPS!! Task in the list to be marked as done is not specified!");
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
        assert task == null;

        String response;
        int index;

        index = Integer.parseInt(command.substring(5));
        Task t = taskList.getStorage().get(index - 1);

        if (t.getStatusIcon().equals("O")) {
            ui.print("     Task is already marked as done!");

            response = "Task is already marked as done!";
        } else {
            t.markAsDone();
            ui.print("     Nice! I have marked this task as done :-)");

            response = "Nice! I have marked this task as done :-)";
        }

        return response;
    }
}
