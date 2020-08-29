package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

/**
 * The add command manages the adding of a new task to the
 * task list. Details are in the execute method.
 */
public class AddCommand implements Command {

    /** The subtype of the task - T/D/E */
    private final String type;

    /** The raw description of the task */
    private final String description;

    /**
     * Constructor for the command.
     * @param type the subtype of the task.
     * @param description the raw description of the task.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * The flag required by the main program.
     * In this command's case, the program does not need to exit.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * This command execution is summarized as:
     *
     *     1. Create new task using TaskList method
     *     2. Add the new task to the TaskList
     *     3. Calls Storage to update the txt file
     *     4. Calls Ui to send success message
     *
     * DukeException can be generated upon failure in any
     * of the above steps. It will be caught and sent to
     * the user via the ui.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = TaskList.createTask(type, description);
            tasks.add(task);
            storage.update(tasks.getList());
            String msg = ui.getSuccessMessage("add", task);
            msg += "\n" + ui.getTaskReportMessage(tasks.size());
            ui.sendMessage(msg);
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
        }
    }
}
