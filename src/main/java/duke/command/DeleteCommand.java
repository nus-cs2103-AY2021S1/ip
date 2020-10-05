package duke.command;

import java.util.LinkedList;
import java.util.Optional;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

/**
 * Command that delete a task in task list and storage.
 */
public class DeleteCommand implements ReversibleCommand {
    private final int index;
    private Optional<Task> deletedTask = Optional.empty();
    /**
     * Initializes an DeleteCommand with the given index.
     *
     * @param index the index given.
     */
    public DeleteCommand(int index) {
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
     * Executes the DeleteCommand to delete the task in the given task list and storage,
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
        taskList.remove(index);
        storage.saveList(taskList);
        this.deletedTask = Optional.of(task);
        reversibleCommands.add(this);
        return ui.giveResponse("    Noted. I've removed this task:\n        "
            + task
            + taskList.sizeDescription());
    }

    @Override
    public String undo(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskToReAdd = deletedTask.orElseThrow(() -> new DukeException("I cannot restore the deleted task."));
        taskList.add(index, taskToReAdd);
        storage.saveList(taskList);
        return ui.giveResponse("    OK! I've re-added the task you just deleted:\n        "
            + taskToReAdd
            + taskList.sizeDescription());
    }
}
