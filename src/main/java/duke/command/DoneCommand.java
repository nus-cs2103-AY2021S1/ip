package duke.command;

import duke.DukeException;
import duke.ExceptionTypeEnum;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand implements Command {
    int index; // 0-based

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at a specified index (0-based) as done/finished.
     *
     * @param tasks current list of tasks to be modified from
     * @param ui user interface to show messages
     * @param storage storage interface to write the current list of tasks in
     * @throws DukeException if the index passed is invalid, or if the task is already marked as done
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(index < 0 || index >= tasks.size()) {
            throw new DukeException(ExceptionTypeEnum.INVALID_ITEM_NUMBER);
        }

        Task task = tasks.get(index);
        if(task.isDone) {
            throw new DukeException(ExceptionTypeEnum.ITEM_ALREADY_DONE);
        }
        task.markAsDone();
        ui.print("Nice, I've marked this task as done:", "\t" + task.toString());
        storage.write(tasks);
    }
}
