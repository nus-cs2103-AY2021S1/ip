package duke.command;

import java.util.List;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class DeleteCommand extends Command {
    protected static List<String> aliases;
    private final int indexEntryToDelete;


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
