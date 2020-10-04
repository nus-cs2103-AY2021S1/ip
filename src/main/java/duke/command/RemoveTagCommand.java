package duke.command;

import java.util.Arrays;
import java.util.List;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to remove tags from tasks.
 */
public class RemoveTagCommand extends Command {
    private final int taskIdx;
    private final List<String> tagsToRemove;

    /**
     * Creates a new {@code TagCommand}.
     *
     * @param taskIdx Index of the task to be processed.
     * @param tagsToRemove Tags to be removed from the task.
     */
    public RemoveTagCommand(int taskIdx, String... tagsToRemove) {
        this.taskIdx = taskIdx;
        this.tagsToRemove = Arrays.asList(tagsToRemove);
    }

    /**
     * Executes the command and deletes the tags from the task.
     *
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     * @throws DukeException If the TaskList is empty, or if the provided index is not associated with a task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Your list is empty!");
        }

        if (taskIdx > tasks.size() || taskIdx <= 0) {
            throw new DukeException("No task with this ID!");
        }

        Task task = tasks.getTask(taskIdx);
        boolean isAnyTagNotFound = false;

        for (String tag : tagsToRemove) {
            if (!task.removeTag(tag)) {
                isAnyTagNotFound = true;
            }
        }

        String output = "Done!";
        if (isAnyTagNotFound) {
            output += "\nOne or more tags provided do not exist in the task, so I skipped them.";
        }

        storage.saveData(tasks);
        ui.botOutput(output);
    }
}
