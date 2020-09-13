package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends SimpleCommand {

    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes or complete a task, depending on the task type.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws DukeException If an error is found in the user input.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        checkValidity(input, SimpleCommandType.DONE, tasks);
        int digit = Integer.parseInt(input);
        Task current = tasks.get(digit - 1);
        tasks.delete(digit - 1);
        storage.update(tasks);
        return ui.deleteTask(current, tasks.size());
    }
}
