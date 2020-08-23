package duke.command;

import duke.*;
import duke.exception.*;

public class DeleteCommand extends Command {

    public DeleteCommand(String command, String extra) {
        super(command, extra);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeInvalidArgumentException, DukeInvalidTaskException {
        try {
            int taskNum = Integer.parseInt(extra) - 1;
            ui.printDeleted(tasks.getTask(taskNum), tasks.getSize() - 1);
            tasks.deleteTask(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(command);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException();
        }
    }
}
