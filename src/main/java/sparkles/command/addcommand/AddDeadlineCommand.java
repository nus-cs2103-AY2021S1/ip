package sparkles.command.addcommand;

import sparkles.SparklesException;
import sparkles.command.Command;
import sparkles.task.Deadline;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represents an AddDeadlineCommand.
 */
public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command
     * Add the deadline to the TaskList and
     * local disk file.
     * @param taskList
     * @param ui
     * @param storage
     * @throws SparklesException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        String[] arr = command.split(" /by ");

        String desc;
        Task task;
        String by;

        try {
            desc = arr[0].substring(9).trim();
            by = arr[1];
            task = new Deadline(desc, by);
            ui.print("     Got it. I've added this task");
            task.printTask();

            taskList.add(task);
            ui.printListSize(taskList.listSize());
        } catch (Exception ex) {
            throw new SparklesException("     OOPS!! The description and deadline of a Deadline cannot be empty!");
        } finally {
            storage.updateFile(taskList.getStorage());
        }
    }

}
