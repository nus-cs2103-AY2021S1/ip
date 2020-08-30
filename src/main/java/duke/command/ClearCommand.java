package duke.command;

import java.io.IOException;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;



public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        tasks.clear();
        ui.displayThis("List is cleared");
        try {
            fileHandler.writeToFile(tasks.getList());
        } catch (IOException e) {
            throw new DukeException("Erorr clearing lists in storage");
        }

    }
}
