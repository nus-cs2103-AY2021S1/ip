package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that deletes a task from the taskList when executed. */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted. */
    private int index;

    /** Constructs a new DeleteCommand object with the specified index of the task.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /** Deletes the task from TaskList's list,
     * prints out the DeleteCommand message in Duke format and
     * saves the list in the hard disk.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert index > taskList.getTasks().size() : "Oh no! There is an error with the DeleteCommand numbering logic.";
        try {
            setDialog(ui.formatDeleteTask(taskList.getTasks(), index));
            taskList.deleteTask(index);
            storage.saveTaskList(taskList.getTasks());;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
