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

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        taskList.remove(index);
        storage.saveTasks(taskList);

        assert !taskList.getTasks().contains(task) : "Task is not removed from the taskList!";

        return Message.concatLines(Message.MESSAGE_DELETE, task.toString(),
                Ui.LINE_SEPARATOR, Message.getTotalTaskMessage(taskList));
    }
}
