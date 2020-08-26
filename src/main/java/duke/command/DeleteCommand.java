package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
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
     * @param stringArray Tokenized array form of the input command string.
     */
    public DeleteCommand(String[] stringArray) {
        super(stringArray);
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.isFirstIndexEmpty()) {
            throw new DukeException("Oops, please enter a task number after your command!");
        }
        try {
            int taskNumber = Integer.parseInt(getFirstIndex());
            if (taskNumber <= 0 || !tasks.isTaskPresent(taskNumber - 1)) {
                throw new DukeException("Oops, enter a task number that already exists in the list. "
                        + "(starting from 1 to " + tasks.totalTask() + ").");
            }
            Task taskToDelete = tasks.getTask(taskNumber - 1);
            tasks.deleteTask(taskNumber - 1);
            ui.showDelete(tasks, taskToDelete);
        } catch (NumberFormatException e) {
            throw new DukeException("Task Number to delete must be an Integer!");
        }
        storage.write(tasks);
    }
}
