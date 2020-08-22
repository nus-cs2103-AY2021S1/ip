package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.Ui;

public class AddCommand implements Command {
    Task t;

    public AddCommand(Task t) {
        this.t = t;
    }

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        s.save(t);
    }
}
