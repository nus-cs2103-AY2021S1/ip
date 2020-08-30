package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.task.Task;

/**
 * Represents a <code>Command</code> telling Duke to mark a certain <code>Task</code> as done.
 */
public class DoneCommand implements Command {
    private String[] command;

    public DoneCommand(String[] command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws DukeException {
        if (command.length != 2) {
            throw new DukeException("Wrong format.");
        }
        try {
            Task task = taskList.markAsDone(Integer.parseInt(command[1]));
            storage.writeToFile(taskList);
            return Message.getTaskDone(task);
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong format.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
