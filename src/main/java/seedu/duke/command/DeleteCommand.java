package seedu.duke.command;

import java.io.IOException;

import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.exception.InvalidCommandFormatException;
import seedu.duke.exception.InvalidTaskException;
import seedu.duke.task.Task;

/**
 * Represents a <code>Command</code> telling Duke to delete a certain <code>Task</code>.
 */
public class DeleteCommand implements Command {
    private String[] command;

    public DeleteCommand(String[] command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException,
            InvalidTaskException, IOException {
        if (command.length != 2) {
            throw new InvalidCommandFormatException("Wrong format for delete command.");
        }
        try {
            Task task = taskList.delete(Integer.parseInt(command[1]));
            storage.writeToFile(taskList);
            return Message.getTaskDeleted(task);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please enter the number of the task you wish to delete.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
