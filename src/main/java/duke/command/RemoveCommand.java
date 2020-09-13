package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

/**
 * The remove command allows user to indicate if they want to
 * remove all tasks in the list or remove a specific task via
 * a task number. Details are in the execute method.
 */
public class RemoveCommand implements Command {

    private final int index;

    private final boolean isEntireList;

    public RemoveCommand(int index) {
        this.index = index;
        isEntireList = false;
    }

    public RemoveCommand() {
        index = -1;
        isEntireList = true;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * This command execution is summarized as:
     *
     *     if the user calls for removing all tasks,
     *         1. Clear the task list
     *         2. Calls Storage to update the txt file
     *         3. Calls Ui to send success message
     *     if the user gives a task number,
     *         1. Gets the removed task from the task list
     *         2. Calls Storage to update the txt file
     *         3. Calls Ui to send success message
     *
     * DukeException can be generated upon failure in any
     * of the above steps. It will be caught and sent to
     * the user via the ui.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (isEntireList) {
            tasks.removeAll();
            storage.update(tasks.getList());
            String msg = "Your list has been cleared!! Yay~";
            ui.sendMessage(msg);
            return msg;
        }
        try {
            Task task = tasks.remove(index);
            storage.update(tasks.getList());
            String msg = ui.getSuccessMessage("remove", task);
            msg += "\n" + ui.getTaskReportMessage(tasks.size());
            ui.sendMessage(msg);
            return msg;
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
