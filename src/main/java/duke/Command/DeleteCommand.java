package duke.command;

import duke.Storage;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Message;
import duke.ui.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a <code>DeleteCommand</code> Object given index of the task to be deleted.
     *
     * @param index The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        taskList.remove(index);
        storage.saveTasks(taskList);
        return Message.concatLines(Message.MESSAGE_DELETE, task.toString(),
                Ui.LINE_SEPARATOR, Message.getTotalTaskMessage(taskList));
    }
}
