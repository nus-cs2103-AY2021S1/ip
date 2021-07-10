package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.response.Response;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command, exiting the program.
     *
     * @param taskList A TaskList instance.
     * @param storage A Storage instance.
     * @throws DukeException if the tasks cannot be saved.
     */
    @Override
    public Response execute(TaskList taskList, Storage storage) {
        return new Response("Bye. Hope to see you again soon!", false, this.isExit());
    }

    /**
     * Returns true to indicate that the program should exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
