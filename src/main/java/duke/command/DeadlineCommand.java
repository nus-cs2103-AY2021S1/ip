package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Add a new deadline into the task list
 */
public class DeadlineCommand extends Command {
    Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Insert a new deadline into tasklist, and save it to storage file
     *
     * @param taskList current task list
     * @param ui       text ui interface
     * @param storage  storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(deadline);
        storage.save(taskList);

        int size = taskList.size();
        ui.printAddConfirmation(taskList.show(size - 1), size);
    }
}
