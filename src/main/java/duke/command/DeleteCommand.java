package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.parser.TaskIndexStringChecker;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a delete command. A DeleteCommand object represents a command
 * to delete a task from a TaskList. This deleted task can be either a todo, deadline or event.
 */
public class DeleteCommand extends Command {

    /**
     * Creates a new DeleteCommand.
     *
     * @param userStrings Tokenized array form of the input command string.
     */
    public DeleteCommand(String[] userStrings) {
        super(userStrings);
    }

    /**
     * Executes the deletion of tasks with the task index and prints notifications to users once that is successful.
     * Also writes the changed task list to a user-specified file.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @throws DukeException If the user-specified task index is not an Integer or not found in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = new TaskIndexStringChecker(getArray()).checkTask(tasks);
        Task taskToDelete = tasks.getTask(taskNumber - 1);
        tasks.deleteTask(taskNumber - 1);
        storage.write(tasks);
        return ui.showDelete(tasks, taskToDelete);
    }
}
