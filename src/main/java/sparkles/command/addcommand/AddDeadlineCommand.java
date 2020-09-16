package sparkles.command.addcommand;

import sparkles.SparklesException;
import sparkles.task.Deadline;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represents an AddDeadlineCommand.
 */
public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command
     * Add the deadline to the TaskList and
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
     *                           or ArrayIndexOutOfBoundsException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        String[] arr = command.split(" /by ");

        String desc;
        String response;
        String by;
        Task task;

        try {
            desc = arr[0].substring(9).trim();
            by = arr[1];
            task = new Deadline(desc, by);

            response = this.getResponse(ui, task, taskList);
        } catch (Exception ex) {
            throw new SparklesException("     OOPS!! The description and deadline of a Deadline cannot be empty!");
        } finally {
            storage.updateFile(taskList.getStorage());
        }
        return response;
    }

}
