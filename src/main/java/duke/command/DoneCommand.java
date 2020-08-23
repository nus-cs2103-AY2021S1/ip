package duke.command;

import duke.*;
import duke.exception.*;;

public class DoneCommand extends Command {

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
}
