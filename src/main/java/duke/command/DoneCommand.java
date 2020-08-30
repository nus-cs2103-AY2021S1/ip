package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a done command. A DoneCommand object represents a command
 * to mark a task as done. This task can be either a todo, deadline or event.
 */
public class DoneCommand extends Command {

    /**
     * Creates a new DoneCommand.
     *
     * @param userStrings Tokenized array form of the input command string.
     */
    public DoneCommand(String[] userStrings) {
        super(userStrings);
    }

    /**
     * Executes the marking of tasks as done with the task index and prints notifications to users once that is
     * successful. Also writes the changed task list to a user-specified file.
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
                throw new DukeException("Oops, enter a task number that already exists in the list "
                        + "(starting from 1 to " + tasks.totalTask() + ").");
            }
            Task taskToMarkDone = tasks.getTask(taskNumber - 1);
            taskToMarkDone.markDone();
            ui.showDone(taskToMarkDone);
        } catch (NumberFormatException e) {
            throw new DukeException("Task Number to mark done must be an Integer!");
        }
        storage.write(tasks);
    }
}

