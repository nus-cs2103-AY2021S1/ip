package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

public interface Command {

    void execute(String command, Storage s, Ui ui) throws DukeException;

}
