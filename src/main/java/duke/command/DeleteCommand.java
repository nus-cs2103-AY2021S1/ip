package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;
import java.util.List;


public class DeleteCommand extends Command {
    private final int indexEntryToDelete;
    protected static List<String> aliases;


    public DeleteCommand(int indexEntryToDelete) {
        this.indexEntryToDelete = indexEntryToDelete;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            ui.displayThis("OKay, I've remove this task: \n" + tasks.delete(indexEntryToDelete)
                    + "\nNow you have " + tasks.getSize() + " tasks in the list");
            fileHandler.writeToFile(tasks.getList());
        } catch (Exception ex) {
            throw new DukeException("This task does not exist");
        }
    }
}
