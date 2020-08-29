package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.tool.TaskList;

/**
 * Represent the command that to clear all data.
 */
public class ClearCommand implements Command {
    /**
     * Message after clear command is executed
     */
    private static final String CLEAR_MESSAGE = "I have cleared all tasks!";

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return ClearCommand.CLEAR_MESSAGE + "\n\t";
    }

}
