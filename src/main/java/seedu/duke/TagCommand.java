package seedu.duke;

/**
 * Represents a command to tag a task
 */
public class TagCommand extends Command {
    private final int index;
    private final String tag;

    /**
     * Initializes tag command
     * @param index The index of the task user wants to tag
     * @param tag The word which the user wants to tag the task object with
     */
    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    /**
     * Tags the task from the task list and shows the task to the user.
     * Saves the new task list to the disk as well.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskTagged = tasks.tagTask(index - 1, tag);
        ui.showTaskTagged(taskTagged);
        storage.saveTaskList(tasks);
    }
}
