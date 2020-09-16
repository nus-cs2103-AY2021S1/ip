package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

/**
 * The remove command is in charge of removing tasks by
 * specific task number, tasks that are done, or clearing the entire list.
 */
public class RemoveCommand implements Command {

    private final int index;

    private final boolean isRemoveEntireList;

    private final boolean isRemoveDone;

    /**
     * Constructs the remove command which targets a task.
     * @param index the task number.
     */
    public RemoveCommand(int index) {
        this.index = index;
        isRemoveEntireList = false;
        isRemoveDone = false;
    }

    /**
     * Constructs the remove command which targets the entire task list
     * or targets tasks that are done.
     */
    public RemoveCommand(String type) {
        isRemoveEntireList = type.equals("all");
        isRemoveDone = type.equals("done");
        index = -1;
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
        if (isRemoveEntireList) {
            tasks.removeAll();
            storage.update(tasks.getList());
            String msg = ui.getClearListMessage();
            ui.sendMessage(msg);
            return msg;
        }
        if (isRemoveDone) {
            tasks.removeDoneTasks();
            storage.update(tasks.getList());
            String msg = ui.getRemoveDoneMessage();
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
