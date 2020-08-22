package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class CheckCommand implements Command {

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        s.checkDate(command.substring(5));
    }
}
