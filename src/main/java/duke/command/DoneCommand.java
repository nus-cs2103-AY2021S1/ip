package duke.command;

import java.util.LinkedList;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

/**
 * Command that complete a task in task list and storage.
 */
public class DoneCommand implements ReversibleCommand {
    private final int index;

    /**
     * Initializes an DoneCommand with the given index.
     *
     * @param index the index given.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Returns whether this is an exit command.
     *
     * @return false at all times.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the DoneCommand to mark the task as done in the given task list and storage,
     * and print the response using the given ui.
     *
     * @param taskList the task list to be updated.
     * @param ui       the ui that handles inputs and outputs.
     * @param storage  the permanent storage of task list.
     * @param reversibleCommands
     * @throws DukeException if the user has entered an illegal index,
     *                       or the task list cannot be saved to the storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage,
                          LinkedList<ReversibleCommand> reversibleCommands) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("The task index should be an index on the list!");
        }
        Task task = taskList.get(index);
        taskList.markAsDone(index);
        storage.saveList(taskList);
        reversibleCommands.add(this);
        return ui.giveResponse("\tNice! I've marked this task as done:\n\t\t" + task);
    }
    @Override
    public String undo(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markAsUndone(index);
        Task task = taskList.get(index);
        storage.saveList(taskList);
        return ui.giveResponse("\tOK! I've unchecked the task:\n\t\t" + task);
    }
}
