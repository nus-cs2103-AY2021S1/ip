package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class DoneCommand implements Command {

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        int taskInd = Integer.parseInt(command.substring(5));
        s.markDone(taskInd - 1);
    }

}
