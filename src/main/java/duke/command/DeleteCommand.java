package duke.command;

import java.io.IOException;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.exception.InvalidCommandFormatException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.ui.Message;

/**
 * Represents a <code>Command</code> telling Duke to delete a certain <code>Task</code>.
 */
public class DeleteCommand implements Command {
    private String[] command;

    public DeleteCommand(String[] command) {
        this.command = command;
    }

    @Override
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

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DeleteCommand) {
            return Arrays.equals(this.command, ((DeleteCommand) obj).command);
        } else {
            return false;
        }
    }
}
