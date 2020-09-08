package dd.commands;

import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

/**
 * A delete command with a particular user input modifies the task list
 * in the system by deleting the specified task.
 */
public class DeleteCommand extends Command {

    /**
     * Class Constructor.
     *
     * @param command Command given.
     * @param item Details of item being deleted.
     */
    public DeleteCommand(String command, String item) {
        super(command, item);
    }

    /**
     * Deletes the task as given in item.
     *
     * @param tasks Current TaskList to modify.
     * @param ui Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @return Output to confirm deleted task if successful, and indicate the updated task list size.
     * @throws DukeException If invalid task number is given in item.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, DataStorage ds) throws DukeException {
        int delNum;

        try {
            delNum = Integer.parseInt(item);
        } catch (NumberFormatException ignored) {
            delNum = 0;
        }

        if (delNum > 0 && delNum <= tasks.getTaskSize()) {
            int initialNum = tasks.getTaskSize();
            String deletedTask = ui.printDeletedTask(tasks.getTask(delNum - 1));
            tasks.deleteTask(delNum - 1);

            assert initialNum - tasks.getTaskSize() == 1 : "failed to delete task";

            return deletedTask + "\n" + ui.printTasksSize(tasks.getTaskSize());
        } else {
            throw new DukeException().invalidTaskNumber();
        }
    }
}
