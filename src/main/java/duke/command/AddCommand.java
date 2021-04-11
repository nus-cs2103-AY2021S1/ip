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

    private final String type;
    private final String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = TaskList.createTask(type, description);
            tasks.add(task);
            storage.update(tasks.getList());
            String msg = ui.getTaskSuccessMessage("add", task);
            msg += "\n" + ui.getTaskReportMessage(tasks.size());
            ui.sendMessage(msg);
            return msg;
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
