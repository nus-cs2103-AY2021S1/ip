package duke.Command;

import duke.Storage;

import duke.Exception.DukeException;

import duke.Task.Task;
import duke.Task.TaskList;

import duke.Ui.Message;
import duke.Ui.Ui;

/**
 * Marks a task as done.
 */
public class CompleteCommand extends Command {

    private final int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.saveTasks(taskList);
        return Message.MESSAGE_DONE + task.toString();
    }
}