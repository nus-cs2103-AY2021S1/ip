package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.parser.TaskIndexStringChecker;
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
     * @param userString Tokenized array form of the input command string.
     */
    public DoneCommand(String[] userString) {
        super(userString);
    }

    /**
     * Executes the marking of tasks as done with the task index and prints notifications to users once that is
     * successful. Also writes the changed task list to a user-specified file.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @return String to be printed out.
     * @throws DukeException If the user-specified task index is not an Integer or not found in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = new TaskIndexStringChecker(getStringArray()).checkTask(tasks);
        Task taskToMarkDone = tasks.getTask(taskNumber - 1);
        taskToMarkDone.markDone();
        storage.write(tasks);
        return ui.showDone(taskToMarkDone);
    }
}

