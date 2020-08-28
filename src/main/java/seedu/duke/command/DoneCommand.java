package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.Task;

/**
 * Represents a <code>Command</code> telling Duke to mark a certain <code>Task</code> as done.
 */
public class DoneCommand implements Command {
    String[] command;

    public DoneCommand(String[] command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (command.length != 2) {
            throw new DukeException("Wrong format.");
        }
        try {
            Task task = taskList.markAsDone(Integer.parseInt(command[1]));
            ui.showTaskMarkedDone(task);
            storage.writeToFile(taskList);
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong format.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
