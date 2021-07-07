package command;

import duke.Storage;
import duke.TaskList;
import task.Deadline;
import ui.Ui;

/**
 * Add a new deadline into the task list
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Insert a new deadline into tasklist, and save it to storage file
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(deadline);
        storage.save(tasks);

        int size = tasks.size();
        return new CommandResult(ui.printAddConfirmation(tasks.show(size - 1), size));
    }
}
