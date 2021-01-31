package duke.command;

import java.io.IOException;
import java.util.Arrays;

import duke.exception.InvalidCommandFormatException;
import duke.exception.InvalidTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;

/**
 * Represents a <code>Command</code> telling Duke to mark a certain <code>Task</code> as done.
 */
public class DoneCommand implements Command {
    private String[] command;

    public DoneCommand(String[] command) {
        this.command = command;
    }

    @Override
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

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DoneCommand) {
            return Arrays.equals(this.command, ((DoneCommand) obj).command);
        } else {
            return false;
        }
    }
}
