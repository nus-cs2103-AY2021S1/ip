package sparkles.command.addcommand;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.task.Todo;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represents an AddTodoCommand.
 */
public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command
     * Add the todo task to the TaskList and
     * local disk file.
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
        String desc;
        String response;
        Task task;

        try {
            desc = command.substring(5).trim();
            task = new Todo(desc);

            response = this.getResponse(ui, task, taskList);
        } catch (Exception ex) {
            throw new SparklesException("     OOPS!! The description of a todo cannot be empty!");
        } finally {
            storage.updateFile(taskList.getStorage());
        }
        return response;
    }
}
