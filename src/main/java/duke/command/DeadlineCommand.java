package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(deadline);
        storage.save(tasks);

        int size = tasks.size();
        ui.printAddConfirmation(tasks.show(size - 1), size);
    }
}
