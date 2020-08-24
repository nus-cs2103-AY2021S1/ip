package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class DeadlineCommand implements Command {
    String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = Deadline.of(this.command);
        taskList.add(deadline);
        storage.appendToFile(deadline);
        ui.showTaskAdded(deadline);
    }

    public boolean isDone() {
        return false;
    }
}
