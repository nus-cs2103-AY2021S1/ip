package duke.command;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

public class SortCommand extends Command {
    public SortCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.sort();
        storage.save(tasks);
        return "Your task list has been sorted!";
    }

    @Override
    public String toString() {
        return "sort";
    }
}
