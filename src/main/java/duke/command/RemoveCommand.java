package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

/**
 * The remove command is in charge of removing a task or
 * clearing the entire task list.
 */
public class RemoveCommand implements Command {

    private final int index;

    private final boolean isEntireList;

    /**
     * Constructs the remove command which targets a task.
     * @param index the task number.
     */
    public RemoveCommand(int index) {
        this.index = index;
        isEntireList = false;
    }

    /**
     * Constructs the remove command which targets the entire task list.
     */
    public RemoveCommand() {
        index = -1;
        isEntireList = true;
    }

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (isEntireList) {
            tasks.removeAll();
            storage.update(tasks.getList());
            String msg = ui.getClearListMessage();
            ui.sendMessage(msg);
            return msg;
        }
        try {
            Task task = tasks.remove(index);
            storage.update(tasks.getList());
            String msg = ui.getTaskSuccessMessage("remove", task);
            msg += "\n" + ui.getTaskReportMessage(tasks.size());
            ui.sendMessage(msg);
            return msg;
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
