package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class DeleteCommand implements Command {

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        s.delete(command);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof DeleteCommand;
    }
}
