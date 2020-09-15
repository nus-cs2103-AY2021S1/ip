package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that marks a task in the taskList as done. */
public class DoneCommand extends Command {

    /** The index of the task to be marked as done. */
    private int index;

    /** Constructs a new DoneCommand object with the specified index of the task.
     *
     * @param index The index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /** Marks the task as done,
     * prints out the DoneCommand message in Duke format and
     * saves the list in the hard disk.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert index > taskList.getTasks().size() : "Oh no! There is an error with the DoneCommand numbering logic.";
        try {
            taskList.markTaskAsDone(index);
            setDialog(ui.formatMarkAsDone(taskList.getTasks(), index));
            storage.saveTaskList(taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
