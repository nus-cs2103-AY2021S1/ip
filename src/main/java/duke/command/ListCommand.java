package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand() {
        aliases = new ArrayList<>();
        aliases.add("l");
        aliases.add("list");

    }

    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        if (tasks.isNull()) {
            throw new DukeException("There's nothing in the list :-(");
        } else {
            ui.displayList(tasks.getList(), "Here are your current tasks:");
        }
    }
}
