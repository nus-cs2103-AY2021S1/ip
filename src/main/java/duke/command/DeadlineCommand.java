package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.Deadline;

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
