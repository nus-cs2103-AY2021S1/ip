package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DuplicateTaskException;
import duke.exception.InvalidCommandFormatException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Message;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>Deadline</code>.
 */
public class DeadlineCommand implements Command {
    private String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public Message execute(TaskList taskList, Storage storage) throws InvalidCommandFormatException, IOException {
        try {
            Deadline deadline = createDeadline();
            taskList.add(deadline);
            storage.appendToFile(deadline);
            return Message.getTaskAdded(deadline);
        } catch (DuplicateTaskException e) {
            return new Message(e.getMessage());
        }
    }

    Deadline createDeadline() throws InvalidCommandFormatException {
        if (command.length() <= 9) {
            throw new InvalidCommandFormatException("Deadline cannot be empty.");
        }
        String[] split = command.substring(9).trim().split("\\s+/by\\s+");
        if (split.length != 2) {
            throw new InvalidCommandFormatException("Wrong format for deadline command.");
        }
        try {
            return new Deadline(split[0], LocalDate.parse(split[1]));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Please enter a valid date in the yyyy-mm-dd format.");
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
        } else if (obj instanceof DeadlineCommand) {
            return this.command.equals(((DeadlineCommand) obj).command);
        } else {
            return false;
        }
    }
}
