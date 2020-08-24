package duke.command;

import duke.*;
import duke.exception.*;;

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
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeInvalidArgumentException, DukeInvalidTaskException {
        try {
            int taskNum = Integer.parseInt(extra) - 1;
            tasks.getTask(taskNum).markAsDone();
            ui.printDone(tasks, taskNum);
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
