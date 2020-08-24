package duke.command;

import duke.*;
import duke.exception.*;

/**
 * Representing a command to delete task.
 */
public class DeleteCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     * @param extra String parsed by Parser object representing
     *              argument for delete command.
     */
    public DeleteCommand(String command, String extra) {
        super(command, extra);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui)
            throws DukeInvalidArgumentException,
            DukeInvalidTaskException {
        try {
            int taskNum = Integer.parseInt(extra)
                    - PARSE_INDEX;
            ui.printDeleted(tasks.getTask(taskNum),
                    tasks.getSize() - TASK_INDEX);
            tasks.deleteTask(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(command);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeleteCommand;
    }
}
