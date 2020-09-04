package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidTaskException;

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
    public String execute(Storage storage, TaskList tasks, Ui ui)
            throws DukeInvalidArgumentException,
            DukeInvalidTaskException {
        assert storage != null : "Storage object cannot be null";
        assert tasks != null : "TaskList object cannot be null";
        assert ui != null : "Ui object cannot be null";
        try {
            int taskNum = Integer.parseInt(extra)
                    - PARSE_INDEX;
            String reply = ui.printDeleted(tasks.getTask(taskNum),
                    tasks.getSize() - TASK_INDEX);
            tasks.deleteTask(taskNum);
            return reply;
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
