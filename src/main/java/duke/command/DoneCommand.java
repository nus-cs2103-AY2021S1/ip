package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

/**
 * The done command is in charge of marking tasks as done when
 * the user indicates via the task number. Details are in th
 * execute method.
 */
public class DoneCommand implements Command {

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * This command execution is summarized as:
     *
     *     1. Gets the task which is marked done by TaskList.
     *     2. Calls Storage to update the txt file
     *     3. Calls Ui to send success message
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
        try {
            Task task = tasks.markDone(index);
            storage.update(tasks.getList());
            String msg = ui.getSuccessMessage("done", task);
            ui.sendMessage(msg);
            return msg;
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
