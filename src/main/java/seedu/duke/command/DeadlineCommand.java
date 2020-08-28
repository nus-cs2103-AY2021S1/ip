package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.Deadline;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>Deadline</code>.
 */
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
