package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements Command {
    int index; // 0-based

    public DeleteCommand(int index) {
        this.index = index;
    }


    /**
     * Deletes the task at a specified index (0-based)
     *
     * @param tasks current list of tasks to be deleted from
     * @param ui user interface to show messages
     * @param storage storage interface to write the current list of tasks in
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(index);
        assert index >= 0 && index < tasks.size() : "Invalid item number";
        tasks.delete(index);
        storage.write(tasks);
        return   ui.format("The following task has been removed successfully:", "\t" + task.toString(),
                "Now you have " + tasks.size() + " items(s) left in the list.");
    }
}
