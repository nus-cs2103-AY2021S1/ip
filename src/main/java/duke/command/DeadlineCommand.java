package duke.command;

import duke.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class DeadlineCommand extends Command {
    Deadline deadline;
    
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(deadline);
        storage.save(taskList);

        int size = taskList.size();
        ui.printAddConfirmation(taskList.show(size - 1), size);
    }
}
