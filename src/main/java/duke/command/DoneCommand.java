package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidTaskException;

/**
 * Represents command to mark task as done.
 */
public class DoneCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     * @param extra String parsed by Parser object representing
     *              argument for done command.
     */
    public DoneCommand(String command, String extra) {
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
            tasks.getTask(taskNum).markAsDone();
            return ui.printDone(tasks, taskNum);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(command);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoneCommand;
    }
}
