package duke.command;

import duke.Storage;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

import static duke.ui.Message.concatLines;
import static duke.ui.Message.MESSAGE_DONE;
import static duke.ui.Ui.LINE_SEPARATOR;

/**
 * Marks a task as done.
 */
public class CompleteCommand extends Command {

    private final int index;

    /**
     * Constructs a <code>CompleteCommand</code> Object given index of the task to be completed.
     *
     * @param index The 1-based index of the task to be completed.
     */
    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.saveTasks(taskList);

        assert task.getStatusCode() == 1 : "The status of the task is not updated!";

        return concatLines(MESSAGE_DONE, task.toString(), LINE_SEPARATOR);
    }
}
