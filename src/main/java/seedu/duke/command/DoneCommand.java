package seedu.duke.command;

import java.io.IOException;

import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.exception.InvalidCommandFormatException;
import seedu.duke.exception.InvalidTaskException;
import seedu.duke.task.Task;

/**
 * Represents a <code>Command</code> telling Duke to mark a certain <code>Task</code> as done.
 */
public class DoneCommand implements Command {
    private String[] command;

    public DoneCommand(String[] command) {
        this.command = command;
    }

    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException,
            InvalidTaskException, IOException {
        if (command.length != 2) {
            throw new InvalidCommandFormatException("Wrong format for done command.");
        }
        try {
            Task task = taskList.markAsDone(Integer.parseInt(command[1]));
            storage.writeToFile(taskList);
            return Message.getTaskDone(task);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please enter the number of the task you wish to mark as done.");
        }
    }

    public boolean isDone() {
        return false;
    }
}
