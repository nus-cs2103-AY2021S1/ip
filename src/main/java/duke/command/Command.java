package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.exception.InvalidCommandFormatException;
import duke.exception.InvalidTaskException;
import duke.ui.Message;

/**
 * Represents a command entered by the user for Duke to execute.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param taskList a list of the user's tasks
     * @param storage  Deals with the saving of the user's tasks.
     * @throws InvalidTaskException          if there is no task at the number entered
     * @throws InvalidCommandFormatException if the command entered does not follow the required format
     * @throws IOException                   if there is a problem writing tasks to the file
     */
    Message execute(TaskList taskList, Storage storage) throws InvalidTaskException, InvalidCommandFormatException,
            IOException;

    /**
     * Determines if this <code>Command</code> causes Duke to stop running.
     *
     * @return whether or not this command terminates the program
     */
    boolean isDone();
}
