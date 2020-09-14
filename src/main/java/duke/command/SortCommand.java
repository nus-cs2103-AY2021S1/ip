package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class SortCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.sortList();
        return "Your task list has been sorted.";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SortCommand;
    }
}
