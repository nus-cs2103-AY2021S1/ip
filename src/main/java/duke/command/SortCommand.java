package duke.command;

import duke.exception.DukeException;
import duke.logic.Tasklist;
import duke.logic.Storage;

public class SortCommand extends Command {

    public SortCommand() {
        super();
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) throws DukeException {
        tasks.sort();
        storage.save(tasks);
        return tasks.toDisplayString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
