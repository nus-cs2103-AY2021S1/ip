package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Adds a tag to a task.
 */
public class TagCommand extends Command {
    private int taskIndex;
    private String tagDescription;

    /**
     * Constructor for a new TagCommand object.
     *
     * @param taskIndex index of task in task list to be tagged.
     * @param tagDescription tag description.
     */
    public TagCommand(int taskIndex, String tagDescription) {
        this.taskIndex = taskIndex - 1;
        this.tagDescription = tagDescription;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui object to output messages.
     * @param storage object to write TaskList to file.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.setTag(taskIndex, tagDescription);
            Ui.display("Nice! I've tagged this task:\n   "
                    + tasks.getTask(taskIndex));
            Storage.writeToFile(tasks.getList());
        } catch (Exception e) {
            throw new DukeException("This task does not exist!");
        }
    }
}
