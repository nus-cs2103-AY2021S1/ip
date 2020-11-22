package duke.command;

import java.util.List;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class DoneCommand extends Command {
    protected static List<String> aliases;
    private final int indexEntryDone;


    public DoneCommand(int indexEntryDone) {
        this.indexEntryDone = indexEntryDone;

    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            ui.displayThis("Nice! I've marked this task as done: \n" + tasks.done(indexEntryDone));
            fileHandler.writeToFile(tasks.getList());
        } catch (Exception ex) {
            throw new DukeException("This task does not exist");
        }
    }
}
