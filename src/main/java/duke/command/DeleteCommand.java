package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class DeleteCommand extends Command {
    private final int entryDelete;


    public DeleteCommand(int entryDelete) {
        this.entryDelete = entryDelete;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            ui.displayThis("OKay, I've remove this task: \n        " + tasks.delete(entryDelete) +
                    "\n    Now you have " + tasks.size() + " tasks in the list");
            fileHandler.writeToFile(tasks.getList());
        } catch (Exception ex) {
            throw new DukeException("This task does not exist");
        }
    }
}