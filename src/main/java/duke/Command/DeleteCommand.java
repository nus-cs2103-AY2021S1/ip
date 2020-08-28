package duke.Command;

import duke.Storage;

import duke.Exception.DukeException;

import duke.Task.Task;
import duke.Task.TaskList;

import duke.Ui.Message;
import duke.Ui.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        taskList.remove(index);
        storage.saveTasks(taskList);
        return Message.MESSAGE_DELETE + task.toString() + Ui.LINE_SEPARATOR
                + Message.getTotalTaskMessage(taskList);
    }
}
