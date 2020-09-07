package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class ClearCommand extends Command {

    public ClearCommand() {
        aliases = new ArrayList<>();
        aliases.add("c");
        aliases.add("clear");
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        tasks.clear();
        ui.displayThis("List is cleared");
        try {
            fileHandler.writeToFile(tasks.getList());
        } catch (IOException e) {
            throw new DukeException("Error clearing lists in storage");
        }

    }
}
