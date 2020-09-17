package duke.command;

import java.util.Arrays;
import java.util.List;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to tag tasks.
 */
public class TagCommand extends Command {
    private final int taskIdx;
    private final List<String> tags;

    /**
     * Creates a new {@code TagCommand}.
     *
     * @param taskIdx Index of the task to be tagged.
     * @param tags Tags to be added to the task.
     */
    public TagCommand(int taskIdx, String... tags) {
        this.taskIdx = taskIdx;
        this.tags = Arrays.asList(tags);
    }

    /**
     * Executes the command and adds the tags to the task.
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
        task.addTags(tags);
        storage.saveData(tasks);
        ui.botOutput("Alright, I have added the tags!");
    }
}
