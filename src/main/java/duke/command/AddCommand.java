package duke.command;

import java.util.LinkedList;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

/**
 * Command that adds a task to the task list and storage.
 */
public class AddCommand implements ReversibleCommand {
    private final Task newTask;

    /**
     * Initializes an AddCommand with the given new Task.
     *
     * @param newTask the new task given.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Returns whether this is an exit command.
     *
     * @return false at all times.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the AddCommand to add the new task to the given task list and storage,
     * and print the response using the given ui.
     *
     * @param taskList the task list to be updated.
     * @param ui       the ui that handles inputs and outputs.
     * @param storage  the permanent storage of task list.
     * @param reversibleCommands
     * @throws DukeException if the task list cannot be saved to the storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage,
                          LinkedList<ReversibleCommand> reversibleCommands) throws DukeException {
        taskList.add(newTask);
        storage.saveList(taskList);
        reversibleCommands.add(this);
        return ui.giveResponse("    Got it. I've added this task:\n        "
            + newTask
            + taskList.sizeDescription());
    }

    @Override
    public String undo(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task removed = taskList.removeLast();
        storage.saveList(taskList);
        return ui.giveResponse("    OK! I've removed the task you just added now:\n        "
            + removed
            + taskList.sizeDescription());
    }
}
