package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.Task;

/**
 * Represents a <code>Command</code> telling Duke to delete a certain <code>Task</code>.
 */
public class DeleteCommand implements Command {
    private String[] command;

    public DeleteCommand(String[] command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws DukeException {
        if (command.length != 2) {
            throw new DukeException("Wrong format.");
        }
        try {
            Task task = taskList.delete(Integer.parseInt(command[1]));
            storage.writeToFile(taskList);
            return new Message(Message.TASK_DELETED + task.toString());
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong format.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
