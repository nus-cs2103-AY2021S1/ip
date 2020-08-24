package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;


public class DeadlineCommand extends Command {
    Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(deadline);
        storage.save(tasks);

        int size = tasks.size();
        ui.printAddConfirmation(tasks.show(size - 1), size);
    }
}
